package com.example.navigation

import androidx.navigation.NavController

/**
 * Этот интерфейс должна реализовывать MainActivity для управления навигацией
 */
interface NavigatorI {
    var navController: NavController?
    fun navigateToFlow(flow: NavigationFlow)
}