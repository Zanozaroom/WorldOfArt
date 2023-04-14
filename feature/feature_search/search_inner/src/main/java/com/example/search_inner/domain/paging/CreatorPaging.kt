package com.example.search_inner.domain.paging

import com.example.api_model.pojo.chicagomuseum.PagingCMArtWork
import com.example.api_model.pojo.retrofit.ChicagoMuseumSearchResponse

interface CreatorPaging {
    fun createPaging(response: ChicagoMuseumSearchResponse): PagingCMArtWork
}
