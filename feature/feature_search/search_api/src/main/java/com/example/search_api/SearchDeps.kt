package com.example.search_api

import com.example.api_model.dao.DaoFavoriteArtChicago
import com.example.api_model.mapper.MapperDataBase
import com.example.api_model.mapper.MapperPojoChicagoRetrofit
import com.example.api_model.retrofit.ChicagoMuseumRetrofit
import com.example.api_model.retrofit.CreatorFields
import com.example.core_api.LoggerApp

interface SearchDeps {
    val retrofitChicago: ChicagoMuseumRetrofit
    val dataBaseFavorite: DaoFavoriteArtChicago
    val mapperPojoChicagoRetrofit: MapperPojoChicagoRetrofit
    val mapperDataBase: MapperDataBase
    val logger: LoggerApp
    val creatorFields: CreatorFields
}