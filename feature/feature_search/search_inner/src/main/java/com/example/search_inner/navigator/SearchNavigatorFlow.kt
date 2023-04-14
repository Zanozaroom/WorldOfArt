package com.example.search_inner.navigator

sealed class SearchNavigatorFlow {
    object ToBack: SearchNavigatorFlow()
    data class ToDeepLinkOpenPageOfArt(val msg: String): SearchNavigatorFlow()
}