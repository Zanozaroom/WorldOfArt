package com.example.auth_inner.utill

sealed class ViewLoginState
object WaitUserDataState: ViewLoginState()
object TryDoItState: ViewLoginState()