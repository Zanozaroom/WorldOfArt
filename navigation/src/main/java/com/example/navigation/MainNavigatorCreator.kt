package com.example.navigation

object MainNavigatorCreator{
    private val navigator by lazy {
        Navigator()
    }
    fun getNav() = navigator
}