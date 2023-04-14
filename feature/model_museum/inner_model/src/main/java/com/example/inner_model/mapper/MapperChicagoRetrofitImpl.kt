package com.example.inner_model.mapper

import com.example.api_model.pojo.retrofit.MuseumArtWorkResponse
import com.example.api_model.pojo.retrofit.OneArtWorkResponse
import com.example.api_model.mapper.MapperPojoChicagoRetrofit
import com.example.api_model.pojo.chicagomuseum.CMArtWork
import javax.inject.Inject


class MapperChicagoRetrofitImpl @Inject constructor(): MapperPojoChicagoRetrofit {

    override fun mapFromNetwork(artWorkResponse: MuseumArtWorkResponse): List<CMArtWork> {
        val baseUrlImage = artWorkResponse.imageLink?.iiifUrl + "/"

        return artWorkResponse.infoArtDto.let { artDto ->
            artDto!!.filter { it.imageLinkId != null }
                .map { infoAboutArt ->
                    val id = infoAboutArt.id ?: MIN_ID
                    val title = infoAboutArt.title ?: EMPTY_RESULT
                    val image = baseUrlImage + infoAboutArt.imageLinkId + END_URL
                    val artist = infoAboutArt.artistName ?: EMPTY_RESULT
                    val dataCreate = infoAboutArt.wasCreateDate ?: EMPTY_RESULT
                    val placeCreate = infoAboutArt.wasCreatePlace ?: EMPTY_RESULT
                    CMArtWork(
                        id = id,
                        title = title,
                        image = image,
                        artist = artist,
                        dataCreate = dataCreate,
                        placeCreate = placeCreate,
                        isFavorite = false
                    )
                }
        }
    }
    override fun mapOneArtFromNetwork(artResponse: OneArtWorkResponse): CMArtWork {
        val baseUrlImage = artResponse.linkImage.iiifUrl + "/"
        return CMArtWork(
            id = artResponse.data.id,
            title = artResponse.data.title ?: EMPTY_RESULT,
            image = baseUrlImage + artResponse.data.imageId + END_URL,
            artist = artResponse.data.artistDisplay ?: EMPTY_RESULT,
            dataCreate = if (artResponse.data.dateEnd != 0) artResponse.data.dateEnd.toString() else EMPTY_RESULT,
            placeCreate = artResponse.data.placeOfOrigin ?: EMPTY_RESULT,
            isFavorite = false
        )

    }
    companion object{
        private const val END_URL = "/full/843,/0/default.jpg"
        private const val MIN_ID = 1
        private const val EMPTY_RESULT = ""
    }
}