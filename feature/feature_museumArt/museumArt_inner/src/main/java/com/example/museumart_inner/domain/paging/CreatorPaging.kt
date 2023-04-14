package com.example.museumart_inner.domain.paging

import com.example.api_model.pojo.chicagomuseum.PagingCMArtWork
import com.example.api_model.pojo.retrofit.MuseumArtWorkResponse

interface CreatorPaging {
    fun createPaging(oldPaging: MuseumArtWorkResponse): PagingCMArtWork
}
