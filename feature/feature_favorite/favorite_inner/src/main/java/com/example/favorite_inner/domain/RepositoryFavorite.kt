package com.example.favorite_inner.domain

import com.example.api_model.pojo.chicagomuseum.CMArtWork
import kotlinx.coroutines.flow.Flow

interface RepositoryFavorite {
    fun getFromDB(): Flow<List<CMArtWork>>
    suspend fun deleteFromDB(id:Int): Boolean
}