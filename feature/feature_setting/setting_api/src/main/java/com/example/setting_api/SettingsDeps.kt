package com.example.setting_api

import com.example.core_api.SharedPrefInterface
import com.example.navigation.NavigatorI


interface SettingsDeps {
    val sharedPref: SharedPrefInterface
    val navigator: NavigatorI
}