package com.example.api_model.mapper

import com.example.api_model.pojo.retrofit.MuseumArtWorkResponse
import com.example.api_model.pojo.retrofit.OneArtWorkResponse
import com.example.api_model.pojo.chicagomuseum.CMArtWork

interface MapperPojoChicagoRetrofit {
    fun mapFromNetwork(artWorkResponse: MuseumArtWorkResponse): List<CMArtWork>
    fun mapOneArtFromNetwork(artResponse: OneArtWorkResponse): CMArtWork
}



