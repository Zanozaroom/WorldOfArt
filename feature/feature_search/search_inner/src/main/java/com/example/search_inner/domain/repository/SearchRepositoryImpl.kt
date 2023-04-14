package com.example.search_inner.domain.repository

import com.example.api_model.mapper.MapperPojoChicagoRetrofit
import com.example.api_model.pojo.chicagomuseum.CMArtWork
import com.example.api_model.pojo.chicagomuseum.PagingCMArtWork
import com.example.api_model.pojo.retrofit.ChicagoMuseumSearchResponse
import com.example.api_model.pojo.retrofit.ErrorChicago
import com.example.api_model.pojo.retrofit.MuseumArtWorkResponse
import com.example.api_model.retrofit.ChicagoMuseumRetrofit
import com.example.api_model.retrofit.CreatorFields
import com.example.api_model.state.EmptyRepoState
import com.example.api_model.state.ErrorLoadRepoState
import com.example.api_model.state.RepoState
import com.example.api_model.state.SuccessRepoState
import com.example.core_api.LoggerApp
import com.example.search_inner.domain.paging.CreatorPaging
import com.example.network.base.*
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(
    private val retrofitApi: ChicagoMuseumRetrofit,
    private val mapperPojoRetrofit: MapperPojoChicagoRetrofit,
    private val creatorPaging: CreatorPaging,
    private val dispatcher: CoroutineDispatcher,
    private val logger: LoggerApp,
    private val creatorFields: CreatorFields
    ): SearchRepository {

    override suspend fun loadSearchArtWorks(
        query: String,
        pagingCMArtWork: PagingCMArtWork
    ): RepoState<PagingCMArtWork> = withContext(dispatcher) {
        val resultSearch = retrofitApi.getSearchCM(query, pagingCMArtWork.nextPage ?: 1)
        val mutableResultArtWork = mutableListOf<CMArtWork>()
        mutableResultArtWork.addAll(pagingCMArtWork.data)
        when (resultSearch) {
            is ApiError -> {
                logger.logInfo("ApiError code: ${resultSearch.code}, body: ${resultSearch.body}")
                ErrorLoadRepoState
            }
            is NetworkError -> {
                logger.logInfo(resultSearch.error.toString())
                ErrorLoadRepoState
            }
            is UnknownError -> {
                logger.logInfo("error: ${resultSearch.error}")
                ErrorLoadRepoState
            }
            is SuccessEmpty -> EmptyRepoState
            is Success -> {
                val newPagCMArtWork = creatorPaging.createPaging(resultSearch.body)
                when (val resultArtWorks = loadArtWorksForSearch(resultSearch.body)) {
                    is Success -> {
                        val artList =
                            mapperPojoRetrofit.mapFromNetwork(resultArtWorks.body)
                        mutableResultArtWork.addAll(artList)
                        SuccessRepoState(newPagCMArtWork.copy(data = mutableResultArtWork))
                    }
                    else -> ErrorLoadRepoState
                }
            }
        }
    }

    private suspend fun loadArtWorksForSearch(
        responseSearch: ChicagoMuseumSearchResponse
    ): CustomNetworkResponse<MuseumArtWorkResponse, ErrorChicago> = withContext(dispatcher) {
        val listIds = responseSearch.data.map { it.id }
        val fields = creatorFields.createWithAllFields()
        retrofitApi.getArtWorkFromSearch(fields, listIds.joinToString())
    }

}
