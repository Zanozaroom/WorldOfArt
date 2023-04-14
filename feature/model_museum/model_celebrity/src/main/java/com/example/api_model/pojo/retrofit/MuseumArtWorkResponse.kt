package com.example.api_model.pojo.retrofit

import com.squareup.moshi.Json

data class MuseumArtWorkResponse(
    @field:Json(name = "pagination")
    var paginationDto: PaginationDto? = null,
    @field:Json(name = "data")
    var infoArtDto: List<InfoArtDto>? = null,
    @field:Json(name = "info")
    var infoLicenseDto: InfoLicenseDto? = null,
    @field:Json(name = "config")
    var imageLink: ImageLink? = null
) {
    data class PaginationDto(
        @field:Json(name = "total")
        val total: Int?,
        @field:Json(name = "limit")
        var limit: Int?,
        @field:Json(name = "total_pages")
        var totalPages: Int?,
        @field:Json(name = "current_page")
        var currentPage: Int?,
        @field:Json(name = "prev_url")
        var prevUrl: String? = null,
        @field:Json(name = "next_url")
        var nextUrl: String? = null
    )

    data class InfoArtDto(
        @field:Json(name = "id")
        var id: Int?,
        @field:Json(name = "title")
        var title: String? = null,
        @field:Json(name = "image_id")
        var imageLinkId: String? = null,
        @field:Json(name = "artist_display")
        var artistName: String? = null,
        @field:Json(name = "place_of_origin")
        var wasCreatePlace: String? = null,
        @field:Json(name = "date_end")
        var wasCreateDate: String? = null,
        @field:Json(name = "artwork_type_title")
        var artworkType: String? = null,
    )

    data class ImageLink(
        @field:Json(name = "iiif_url")
        var iiifUrl: String? = null,
        @field:Json(name = "website_url")
        var websiteUrl: String? = null
    )

    data class InfoLicenseDto(
        @field:Json(name = "license_text")
        var licenseText: String? = null,
        @field:Json(name = "license_links")
        var licenseLinks: List<String>? = null,
        @field:Json(name = "version")
        var version: String? = null
    )
}