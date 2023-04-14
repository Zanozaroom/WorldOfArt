package com.example.api_model.pojo.retrofit

import com.squareup.moshi.Json

data class ChicagoMuseumSearchResponse(
    @field:Json(name = "preference")
    val preference: Any? = null,
    @field:Json(name = "pagination")
    val pagination: Pagination? = null,
    @field:Json(name = "data")
    val data: List<DataDto>,
    @field:Json(name = "info")
    val info: Info? = null,
    @field:Json(name = "config")
    val config: Config? = null
) {
    data class Config(
        @field:Json(name = "iiif_url")
        val iiifUrl: String? = null,
        @field:Json(name = "website_url")
        val websiteUrl: String? = null
    )

    data class DataDto(
        @field:Json(name = "id")
        val id: Int,
        @field:Json(name = "title")
        val title: String,
    )

    data class Info(
        @field:Json(name = "license_text")
        val licenseText: String? = null,
        @field:Json(name = "license_links")
        val licenseLinks: List<String>? = null,
        @field:Json(name = "version")
        val version: String? = null,
    )

    data class Pagination(
        @field:Json(name = "total")
        val total: Int? = 0,
        @field:Json(name = "limit")
        val limit: Int? = 0,
        @field:Json(name = "offset")
        val offset: Int? = 0,
        @field:Json(name = "total_pages")
        val totalPages: Int? = 0,
        @field:Json(name = "current_page")
        val currentPage: Int? = 0
    )

}



