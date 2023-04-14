package com.example.auth_inner.navigation

import androidx.navigation.NavController

interface NavigatorAuth {
    var navController: NavController?
    fun navigate(flow: AuthNavigationFlow)
}