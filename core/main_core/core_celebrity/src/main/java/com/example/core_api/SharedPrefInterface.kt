package com.example.core_api

import kotlinx.coroutines.flow.Flow

interface SharedPrefInterface {
    suspend fun editThemeMode(dayNightMode: Int)
    fun getThemeMode(): Flow<Int>
    suspend fun editIsLogin(isLogin: Boolean)
    fun getIsLogin(): Flow<Boolean>
}