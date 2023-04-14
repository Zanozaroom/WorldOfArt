package com.example.api_model.retrofit

import com.example.api_model.pojo.retrofit.MuseumArtWorkResponse
import com.example.api_model.pojo.retrofit.ChicagoMuseumSearchResponse
import com.example.api_model.pojo.retrofit.ErrorChicago
import com.example.api_model.pojo.retrofit.OneArtWorkResponse
import com.example.network.base.CustomNetworkResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ChicagoMuseumRetrofit {
    @GET("artworks/?")
    suspend fun getArtWorkFromSearch(
        @Query("fields") fields: String,
        @Query("ids") listId: String
    ): CustomNetworkResponse<MuseumArtWorkResponse, ErrorChicago>

    @GET("artworks/?")
    suspend fun getArtWorkCM(
        @Query("fields") fields: String,
        @Query("page") pageForLoading:Int
    ): CustomNetworkResponse<MuseumArtWorkResponse, ErrorChicago>

    @GET("artworks/{id}?")
    suspend fun getOneArtWorkCM(
        @Path("id") id: Int,
        @Query("fields") fields: String
    ): CustomNetworkResponse<OneArtWorkResponse, ErrorChicago>

    @GET("artworks/search?limit=10")
    suspend fun getSearchCM(
        @Query("q") query:String,
        @Query("page") pageForLoading:Int
    ): CustomNetworkResponse<ChicagoMuseumSearchResponse, ErrorChicago>

}

