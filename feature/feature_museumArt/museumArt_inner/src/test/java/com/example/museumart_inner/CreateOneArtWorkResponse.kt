package com.example.museumart_inner

import com.example.api_model.pojo.retrofit.OneArtWorkResponse

object CreateOneArtWorkResponse {
    fun getOneArtWorkResponse(
        data : OneArtWorkResponse.Data = getOneArtWorkResponseData(),
        linkImage: OneArtWorkResponse.LinkImage = getLinkImage(),

        ) = OneArtWorkResponse(
        data = data,
        linkImage = linkImage
    )

    fun getOneArtWorkResponseData(
        id: Int = 157,
        title: String = "Art about cats",
        dateEnd: Int = 1587,
        artistDisplay: String = "Unknown Artist",
        placeOfOrigin: String = "Africa",
        artworkTypeTitle: String = "Any title",
        imageId: String = ""
    ) =OneArtWorkResponse.Data (
        id = id,
        title = title,
        dateEnd = dateEnd,
        artistDisplay = artistDisplay,
        placeOfOrigin = placeOfOrigin,
        artworkTypeTitle = artworkTypeTitle,
        imageId = imageId
    )

    fun getLinkImage(iiifUrl: String = "") = OneArtWorkResponse.LinkImage(iiifUrl = iiifUrl)


}