package com.example.navigation
import androidx.navigation.NavController

/**
 * Класс управляет поведением навигации
 */
class Navigator :NavigatorI {
    override var navController: NavController? = null
    override fun navigateToFlow(flow: NavigationFlow): Unit = when (flow) {
        NavigationBottomMenuFlow -> navController?.navigate(MainGraphDirections.actionBMenuFlow())
            ?: throw IllegalArgumentException("You have to set navController")
        NavigationLoginFlow -> navController?.navigate(MainGraphDirections.actionProfileFlow())
            ?: throw IllegalArgumentException("You have to set navController")
    }
}
