package com.example.museumart_inner.domain.repository

import com.example.api_model.dao.DaoFavoriteArtChicago
import com.example.api_model.mapper.MapperDataBase
import com.example.api_model.mapper.MapperPojoChicagoRetrofit
import com.example.api_model.pojo.chicagomuseum.CMArtWork
import com.example.api_model.pojo.chicagomuseum.PagingCMArtWork
import com.example.api_model.retrofit.ChicagoMuseumRetrofit
import com.example.api_model.retrofit.CreatorFields
import com.example.core_api.LoggerApp
import com.example.museumart_inner.domain.paging.CreatorPaging
import com.example.network.base.ApiError
import com.example.network.base.Success
import com.example.network.base.SuccessEmpty
import com.example.api_model.state.EmptyRepoState
import com.example.api_model.state.ErrorLoadRepoState
import com.example.api_model.state.RepoState
import com.example.api_model.state.SuccessRepoState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RepositoryChicagoArtImpl @Inject constructor(
    private val retrofitApi: ChicagoMuseumRetrofit,
    private val dataBaseFavorite: DaoFavoriteArtChicago,
    private val mapperDataBase: MapperDataBase,
    private val mapperPojoRetrofit: MapperPojoChicagoRetrofit,
    private val creatorPaging: CreatorPaging,
    private val dispatcher: CoroutineDispatcher,
    private val creatorFields: CreatorFields,
    private val loggerApp: LoggerApp
): RepositoryChicagoArt {

    override suspend fun loadArtWorks(
        pagingCMArtWork: PagingCMArtWork
    ): RepoState<PagingCMArtWork> = withContext(dispatcher) {
        val fields = creatorFields.createWithAllFields()
        val result = retrofitApi.getArtWorkCM(fields, pagingCMArtWork.nextPage ?: 1)
        val mutableArtWork = mutableListOf<CMArtWork>()
        mutableArtWork.addAll(pagingCMArtWork.data)
        when (result) {
            is SuccessEmpty -> EmptyRepoState
            is ApiError -> {
                loggerApp.logError("RepositoryChicagoArtImpl ApiError body:${result.body} code: ${result.code}")
                ErrorLoadRepoState
            }
            is Success -> {
                val artWorks = mapperPojoRetrofit.mapFromNetwork(result.body)
                mutableArtWork.addAll(artWorks)
                val newPagCMArtWork = creatorPaging.createPaging(result.body)
                SuccessRepoState(newPagCMArtWork.copy(data = mutableArtWork))
            }
            else -> {
                loggerApp.logError(
                    "RepositoryChicagoArtImpl loadArtWorks Another Error body:${result}")
                ErrorLoadRepoState
            }
        }
    }
    override suspend fun loadOneWork(idArt: Int): RepoState<CMArtWork> = withContext(dispatcher){
        val fields = creatorFields.createWithAllFields()
        when (val result = retrofitApi.getOneArtWorkCM(idArt, fields)) {
            is SuccessEmpty -> EmptyRepoState
            is Success -> {
                val artWorks = result.body
                SuccessRepoState(mapperPojoRetrofit.mapOneArtFromNetwork(artWorks))
            }
            else -> {
                loggerApp.logError(
                    "RepositoryChicagoArtImpl loadOneWork Another Error body:${result}")
                ErrorLoadRepoState
            }
        }
    }

    override suspend fun getOneArtFromDB(idArt: Int): CMArtWork? =
        withContext(dispatcher){
            try{
                val data = dataBaseFavorite.loadOneArtWork(idArt)
                if(data!=null) mapperDataBase.mapFromEntityArtChicago(data)
                else null
            }catch (e: Exception){
                null
            }
        }

    override suspend fun addToDB(addData: CMArtWork): Boolean =
        withContext(dispatcher) {
            try {
                dataBaseFavorite.addToFavoriteChicagoArt(mapperDataBase.mapInEntityArtChicago(addData))
                true
            } catch (e: Exception) {
              false
            }
        }

    override suspend fun deleteFromDB(id: Int): Boolean =
        withContext(dispatcher) {
            try {
                dataBaseFavorite.deleteChicagoArtFromFavorite(id)
                true
            } catch (e: Exception) {
                false
            }
        }
}