package com.example.api_model.pojo.retrofit

import com.squareup.moshi.Json


data class ErrorChicago(
    @field:Json(name ="status")
    val status: Int,
    @field:Json(name ="error")
    val error: String,
    @field:Json(name ="detail")
    val detail: String
)
