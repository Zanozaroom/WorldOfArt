package com.example.inner_model.mapper

import com.example.api_model.pojo.chicagomuseum.CMArtWork
import com.example.api_model.mapper.MapperDataBase
import com.example.api_model.pojo.database.EntityArtChicago
import javax.inject.Inject

class MapperDataBaseImpl @Inject constructor(): MapperDataBase {
    override fun mapInEntityArtChicago(artWork: CMArtWork)= EntityArtChicago(
        id = 0,
        idArt = artWork.id,
        title = artWork.title,
        image = artWork.image,
        artist = artWork.artist,
        dataCreate = artWork.dataCreate,
        placeCreate = artWork.placeCreate,
    )

    override fun mapFromEntityArtChicago(entity: EntityArtChicago)=
        CMArtWork(
            id = entity.idArt,
            title = entity.title,
            image = entity.image,
            artist = entity.artist,
            dataCreate = entity.dataCreate,
            placeCreate = entity.placeCreate,
            isFavorite = true
        )
}