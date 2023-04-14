package com.example.navigation

import androidx.core.net.toUri
import androidx.navigation.NavController
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.NavOptions

fun buildDeepLink(destination: DeepLinkDestination) =
    NavDeepLinkRequest.Builder
        .fromUri(destination.address.toUri())
        .build()

fun NavController.deepLinkNavigateTo(
    deepLinkDestination: DeepLinkDestination,
    popUpTo: Boolean = false
) {
    val builder = NavOptions.Builder()

    if (popUpTo) {
        builder.setPopUpTo(graph.startDestinationId, true)
    }
    val navOptions =
        NavOptions.Builder()
            .setEnterAnim(R.anim.nav_slide_in)
            .setExitAnim(R.anim.nav_slide_out)
            .setPopEnterAnim(R.anim.nav_slide_in)
            .setPopExitAnim(R.anim.nav_slide_out)
            .build()

    navigate(
        buildDeepLink(deepLinkDestination),
        navOptions
    )
}

sealed class DeepLinkDestination(val address: String) {
    class PageOfArt(idArt: String) : DeepLinkDestination("angrysnake://PageOfArt/pageOfArtArgs?msg=${idArt}")
}