package com.example.museumart_inner.domain.repository


import com.example.api_model.pojo.chicagomuseum.CMArtWork
import com.example.api_model.pojo.chicagomuseum.PagingCMArtWork
import com.example.api_model.state.RepoState

interface RepositoryChicagoArt {
    suspend fun loadArtWorks (pagingCMArtWork: PagingCMArtWork): RepoState<PagingCMArtWork>
    suspend fun loadOneWork(idArt:Int): RepoState<CMArtWork>

    suspend fun getOneArtFromDB(idArt: Int): CMArtWork?
    suspend fun addToDB(addData: CMArtWork): Boolean
    suspend fun deleteFromDB(id:Int): Boolean
}