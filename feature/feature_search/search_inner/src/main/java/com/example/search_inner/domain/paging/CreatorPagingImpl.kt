package com.example.search_inner.domain.paging

import com.example.api_model.pojo.chicagomuseum.PagingCMArtWork
import com.example.api_model.pojo.retrofit.ChicagoMuseumSearchResponse
import javax.inject.Inject

class CreatorPagingImpl @Inject constructor(): CreatorPaging {
    override fun createPaging(response: ChicagoMuseumSearchResponse): PagingCMArtWork {
        val currentPage = response.pagination?.currentPage ?: 1
        val totalPages = response.pagination?.totalPages ?: 1
        val nextPage = if(currentPage == totalPages) null else currentPage+1
        val beforePage = if (currentPage == 1) null else currentPage - 1
        return PagingCMArtWork(totalPages = totalPages,
            currentPage = currentPage,
            beforePage = beforePage,
            nextPage = nextPage)
    }
}
