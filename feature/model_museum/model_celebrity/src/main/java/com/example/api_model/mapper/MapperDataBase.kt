package com.example.api_model.mapper

import com.example.api_model.pojo.chicagomuseum.CMArtWork
import com.example.api_model.pojo.database.EntityArtChicago

interface MapperDataBase {
    fun mapInEntityArtChicago(artWork: CMArtWork): EntityArtChicago
    fun mapFromEntityArtChicago(entity: EntityArtChicago): CMArtWork
}