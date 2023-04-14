package com.example.setting_api

import android.app.Application
import android.content.Context

interface SettingsProvider {
    val settingsDeps: SettingsDeps
}
val Context.settingsDeps: SettingsDeps
    get() = when(this){
        is SettingsDeps -> this
        is SettingsProvider -> this.settingsDeps
        is Application -> error("Application must implements SettingsProvider")
        else -> applicationContext.settingsDeps
    }