package com.example.project_alfa_angry_snake.di.ui.activity

import com.example.core_api.SharedPrefInterface
import com.example.navigation.Navigator
import com.example.navigation.NavigatorI

interface ActivityDeps {
    val navigator: NavigatorI
    val sharedPref: SharedPrefInterface
}
