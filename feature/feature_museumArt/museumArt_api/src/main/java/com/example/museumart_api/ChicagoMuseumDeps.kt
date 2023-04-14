package com.example.museumart_api

import com.example.core_api.ImageInputStream
import com.example.core_api.ImageOutputStream
import com.example.api_model.dao.DaoFavoriteArtChicago
import com.example.api_model.mapper.MapperDataBase
import com.example.api_model.mapper.MapperPojoChicagoRetrofit
import com.example.api_model.retrofit.ChicagoMuseumRetrofit
import com.example.api_model.retrofit.CreatorFields
import com.example.core_api.LoggerApp

interface ChicagoMuseumDeps {
    val retrofitChicago: ChicagoMuseumRetrofit
    val dataBaseFavorite: DaoFavoriteArtChicago
    val mapperPojoChicagoRetrofit: MapperPojoChicagoRetrofit
    val mapperDataBase: MapperDataBase
    val inputImageStreamer: ImageInputStream
    val outputImageStreamer: ImageOutputStream
    val logger: LoggerApp
    val creatorFields: CreatorFields
}

