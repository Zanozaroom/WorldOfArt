package com.example.auth_api

import com.example.core_api.LoggerApp
import com.example.core_api.SharedPrefInterface
import com.example.navigation.NavigatorI
import com.google.firebase.auth.FirebaseAuth

interface LoginDeps {
    val sharedPref: SharedPrefInterface
    val firebaseAuth: FirebaseAuth
    val logger: LoggerApp
    val navigator: NavigatorI

}
