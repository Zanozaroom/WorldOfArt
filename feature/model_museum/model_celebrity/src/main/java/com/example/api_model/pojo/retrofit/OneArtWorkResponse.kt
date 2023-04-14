package com.example.api_model.pojo.retrofit

import com.squareup.moshi.Json

data class OneArtWorkResponse(
    @field:Json(name = "data")
    var data: Data,
    @field:Json(name = "config")
    var linkImage: LinkImage
) {
    data class Data(
        @field:Json(name = "id")
        var id: Int,
        @field:Json(name = "title")
        var title: String? = null,
        @field:Json(name = "date_end")
        var dateEnd: Int? = 0,
        @field:Json(name = "artist_display")
        var artistDisplay: String? = null,
        @field:Json(name = "place_of_origin")
        var placeOfOrigin: String? = null,
        @field:Json(name = "artwork_type_title")
        var artworkTypeTitle: String? = null,
        @field:Json(name = "image_id")
        var imageId: String? = null
    )
    data class LinkImage(
        @field:Json(name = "iiif_url")
        var iiifUrl: String? = null,
    )
}





