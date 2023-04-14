package com.example.core_inner.preference

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatDelegate
import com.example.core_api.SharedPrefInterface
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SharedPrefImpl @Inject constructor(
    private val sharedPreferences: SharedPreferences
) : SharedPrefInterface {

    override suspend fun editThemeMode(dayNightMode: Int) {
        withContext(Dispatchers.IO) {
            sharedPreferences.edit().putInt(THEME_MODE_KEY, dayNightMode).apply()
        }
    }

    override fun getThemeMode(): Flow<Int> =
        flowOf(sharedPreferences
            .getInt(THEME_MODE_KEY,AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
        ).flowOn(Dispatchers.IO)




    override suspend fun editIsLogin(isLogin: Boolean) {
        sharedPreferences.edit().putBoolean(USER_IS_LOGIN, isLogin).apply()
    }

    override fun getIsLogin(): Flow<Boolean> =
        flowOf(sharedPreferences.getBoolean(USER_IS_LOGIN, false)).flowOn(Dispatchers.IO)

    companion object {
        private const val THEME_MODE_KEY = "day_night_mode"
        private const val USER_IS_LOGIN = "user_is_login"
    }
}