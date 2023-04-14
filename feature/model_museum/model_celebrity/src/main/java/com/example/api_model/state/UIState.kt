package com.example.api_model.state

sealed class UIState<out T> {
    object Loading: UIState<Nothing>()
    object Error: UIState<Nothing>()
    object ErrorLoadMore: UIState<Nothing>()
    object Empty: UIState<Nothing>()
    data class Success<T>(val data: T): UIState<T>()
}