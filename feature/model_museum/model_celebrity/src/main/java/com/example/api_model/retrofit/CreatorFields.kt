package com.example.api_model.retrofit

interface CreatorFields {
    fun createWithAllFields(): String
    fun createFields(vararg artWorksSortFields: ArtWorksSortFields): String
}
object ArtWorksSortFields{
    const val ID = "id"
    const val TITLE = "title"
    const val IMAGE_ID = "image_id"
    const val ARTIST = "artist_display"
    const val PLACE_CREATED = "place_of_origin"
    const val DATE_CREATED = "date_end"
    const val ARTWORK_MATERIALS_USED = "artwork_type_title"
}