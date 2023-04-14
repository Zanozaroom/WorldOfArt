package com.example.api_model.state

sealed class RepoState<out T>
data class SuccessRepoState<T>(val responseData: T) : RepoState<T>()
object EmptyRepoState: RepoState<Nothing>()
object ErrorLoadRepoState: RepoState<Nothing>()

