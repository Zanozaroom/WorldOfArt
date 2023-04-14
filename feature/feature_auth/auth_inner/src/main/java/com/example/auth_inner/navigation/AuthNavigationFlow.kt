package com.example.auth_inner.navigation

sealed class AuthNavigationFlow {
    object ToLogin: AuthNavigationFlow()
    object ToRegister: AuthNavigationFlow()
}