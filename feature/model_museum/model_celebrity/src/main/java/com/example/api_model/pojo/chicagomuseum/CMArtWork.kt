package com.example.api_model.pojo.chicagomuseum

data class CMArtWork(
    val id: Int,
    val title: String,
    val image: String,
    val artist: String,
    val dataCreate: String,
    val placeCreate: String,
    val isFavorite: Boolean
)
