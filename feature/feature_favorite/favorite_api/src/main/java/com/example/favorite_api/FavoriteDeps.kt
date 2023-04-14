package com.example.favorite_api

import com.example.api_model.dao.DaoFavoriteArtChicago
import com.example.api_model.mapper.MapperDataBase
import com.example.api_model.mapper.MapperPojoChicagoRetrofit
import com.example.api_model.retrofit.ChicagoMuseumRetrofit
import com.example.core_api.LoggerApp


interface FavoriteDeps {
    val retrofitChicago: ChicagoMuseumRetrofit
    val dataBaseFavorite: DaoFavoriteArtChicago
    val mapperPojoChicagoRetrofit: MapperPojoChicagoRetrofit
    val mapperDataBase: MapperDataBase
    val logger: LoggerApp
}
