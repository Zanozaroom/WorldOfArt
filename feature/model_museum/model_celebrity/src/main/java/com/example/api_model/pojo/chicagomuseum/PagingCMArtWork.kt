package com.example.api_model.pojo.chicagomuseum

import com.example.api_model.pojo.retrofit.ErrorChicago

data class PagingCMArtWork(
    val totalPages: Int = 1,
    val currentPage: Int = 1,
    val beforePage: Int? = null,
    val nextPage: Int? = null,
    val data: List<CMArtWork> = emptyList(),
    val errorData: ErrorChicago? = null
)
