package com.example.inner_model.repository

import com.example.api_model.retrofit.ArtWorksSortFields
import com.example.api_model.retrofit.CreatorFields
import javax.inject.Inject

class CreatorFieldsImpl @Inject constructor(): CreatorFields {
    override fun createWithAllFields(): String {
        val listQuery = listOf(
            ArtWorksSortFields.ARTIST,
            ArtWorksSortFields.ID,
            ArtWorksSortFields.TITLE,
            ArtWorksSortFields.IMAGE_ID,
            ArtWorksSortFields.PLACE_CREATED,
            ArtWorksSortFields.DATE_CREATED,
            ArtWorksSortFields.ARTWORK_MATERIALS_USED
        )
        return listQuery.joinToString(separator=",")
    }

    override fun createFields(vararg artWorksSortFields: ArtWorksSortFields): String {
       return artWorksSortFields.toString()
    }
}