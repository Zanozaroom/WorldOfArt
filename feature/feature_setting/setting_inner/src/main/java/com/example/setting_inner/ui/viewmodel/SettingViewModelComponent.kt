package com.example.setting_inner.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.setting_inner.di.SettingsComponent
import com.example.setting_api.settingsDeps
import com.example.setting_inner.di.DaggerSettingsComponent

class SettingViewModelComponent(application: Application): AndroidViewModel(application) {
    val settingsComponent: SettingsComponent by lazy {
        DaggerSettingsComponent.factory().create(application.settingsDeps)
    }

}