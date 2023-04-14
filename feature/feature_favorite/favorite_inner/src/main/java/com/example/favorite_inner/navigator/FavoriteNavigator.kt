package com.example.favorite_inner.navigator

import androidx.navigation.NavController

interface FavoriteNavigator {
    var navController: NavController?
    fun navigate(favoriteNavigationFlow: FavoriteNavigationFlow)
}