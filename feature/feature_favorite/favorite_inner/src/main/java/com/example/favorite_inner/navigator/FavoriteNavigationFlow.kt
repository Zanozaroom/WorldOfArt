package com.example.favorite_inner.navigator

sealed class FavoriteNavigationFlow{
    data class ToDeepLinkPageOfArt(val msg: String): FavoriteNavigationFlow()
}
