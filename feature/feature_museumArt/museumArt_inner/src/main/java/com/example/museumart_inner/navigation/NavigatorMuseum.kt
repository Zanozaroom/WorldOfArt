package com.example.museumart_inner.navigation

import androidx.navigation.NavController

interface NavigatorMuseum {
    var navController: NavController?
    fun navFlowMuseum(flow: FlowMuseumNav)
}