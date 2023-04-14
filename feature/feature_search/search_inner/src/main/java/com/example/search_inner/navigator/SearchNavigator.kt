package com.example.search_inner.navigator

import androidx.navigation.NavController

interface SearchNavigator {
    var navController: NavController?
    fun navigate(flow: SearchNavigatorFlow)
}