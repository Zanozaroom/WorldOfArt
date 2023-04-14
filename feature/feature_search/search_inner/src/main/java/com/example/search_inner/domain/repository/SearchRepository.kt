package com.example.search_inner.domain.repository

import com.example.api_model.pojo.chicagomuseum.PagingCMArtWork
import com.example.api_model.state.RepoState

interface SearchRepository {
    suspend fun loadSearchArtWorks(query: String,pagingCMArtWork: PagingCMArtWork): RepoState<PagingCMArtWork>


}