package com.example.network.base

import java.io.IOException

sealed class CustomNetworkResponse<out T : Any, out U : Any>

/**
 * Success response with body
 */
data class Success<T : Any>(val body: T) : CustomNetworkResponse<T, Nothing>()

/**
 *Success response but body is null
 */
object SuccessEmpty : CustomNetworkResponse<Nothing, Nothing>()

/**
 * Failure response with body
 */
data class ApiError<U : Any>(val body: U?, val code: Int) : CustomNetworkResponse<Nothing, U>()

/**
 * Network error
 */
data class NetworkError(val error: IOException) : CustomNetworkResponse<Nothing, Nothing>()

/**
 * For example, json parsing error
 */
data class UnknownError(val error: Throwable?) : CustomNetworkResponse<Nothing, Nothing>()
