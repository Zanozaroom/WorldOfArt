package com.example.core_api.exception

sealed class AppException: Exception()
data class NetworkUrlException(val errorMessage: String): AppException()
data class PhoneSavedException(val errorMessage: String): AppException()