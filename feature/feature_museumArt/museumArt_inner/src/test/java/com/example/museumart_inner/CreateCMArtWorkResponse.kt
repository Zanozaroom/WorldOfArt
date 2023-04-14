package com.example.museumart_inner

import com.example.api_model.pojo.retrofit.MuseumArtWorkResponse

object CreateCMArtWorkResponse {
    fun getCMArtWorkResponse(
        currentPage: Int,
        totalPages: Int,
        infoAboutArtDto: List<MuseumArtWorkResponse.InfoArtDto> = getListInfoAboutArtDto(),
        infoLicenseDto: MuseumArtWorkResponse.InfoLicenseDto = getInfoLicenseDto(),
        imageLink: MuseumArtWorkResponse.ImageLink = getImageLink()
    ):MuseumArtWorkResponse {
        val paginationDto = getPaginationDto(
            totalPages = totalPages,
            currentPage = currentPage
        )
        return MuseumArtWorkResponse(
            paginationDto = paginationDto,
            infoArtDto = infoAboutArtDto,
            infoLicenseDto = infoLicenseDto,
            imageLink = imageLink)
    }

    private fun getPaginationDto(
        total: Int = 1000,
        limit: Int = 10,
        totalPages: Int,
        currentPage: Int,
        prevUrl: String = "",
        nextUrl: String = ""
    ) = MuseumArtWorkResponse.PaginationDto(
        total = total,
        limit = limit,
        totalPages = totalPages,
        currentPage = currentPage,
        prevUrl = prevUrl,
        nextUrl = nextUrl
    )

    private fun getListInfoAboutArtDto() = listOf(
        getInfoArtDto(),
        getInfoArtDto(),
        getInfoArtDto(),
        getInfoArtDto()
    )

    private fun getInfoArtDto(
        id: Int = 254,
        title: String = "Old time",
        imageLinkId: String = "",
        artistName: String = "Van Gog",
        wasCreatePlace: String = "Chicago",
        wasCreateDate: String = "1862",
        artworkType: String = "Paint",
    ) = MuseumArtWorkResponse.InfoArtDto(
        id = id,
        title = title,
        imageLinkId = imageLinkId,
        artistName = artistName,
        wasCreatePlace = wasCreatePlace,
        wasCreateDate = wasCreateDate,
        artworkType = artworkType
    )

    private fun getInfoLicenseDto(
        licenseText: String = "",
        licenseLinks: List<String> = listOf(),
        version: String = ""
    ) = MuseumArtWorkResponse.InfoLicenseDto(
        licenseText = licenseText,
        licenseLinks = licenseLinks,
        version = version
    )
    private fun getImageLink(
        iiifUrl: String = "",
        websiteUrl: String = ""
    ) = MuseumArtWorkResponse.ImageLink(
        iiifUrl = iiifUrl,
        websiteUrl = websiteUrl
    )


}