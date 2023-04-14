package com.example.favorite_inner.domain

import com.example.api_model.dao.DaoFavoriteArtChicago
import com.example.api_model.mapper.MapperDataBase
import com.example.api_model.pojo.chicagomuseum.CMArtWork
import com.example.core_api.LoggerApp
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RepositoryFavoriteImpl @Inject constructor(
    private val dispatcher: CoroutineDispatcher,
    private val dataBaseFavorite: DaoFavoriteArtChicago,
    private val mapperDataBase: MapperDataBase,
    private val loggerApp: LoggerApp
) : RepositoryFavorite {
    override fun getFromDB(): Flow<List<CMArtWork>> =
        dataBaseFavorite.loadFavoriteChicagoArt().map {
            it.map { entity -> mapperDataBase.mapFromEntityArtChicago(entity) }
        }.flowOn(dispatcher)

    override suspend fun deleteFromDB(id: Int): Boolean =
        withContext(dispatcher) {
            try {
                dataBaseFavorite.deleteChicagoArtFromFavorite(id)
                true
            } catch (e: Exception) {
                loggerApp.logError("RepositoryFavoriteImpl: $e")
                false
            }
        }
}
