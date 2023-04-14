package com.example.setting_inner.di

import com.example.setting_inner.ui.fragment.SettingsFragment
import com.example.setting_api.SettingsDeps
import dagger.Component

@Component(dependencies = [SettingsDeps::class], modules = [SettingsModule::class])
interface SettingsComponent {
    fun inject(settingsFragment: SettingsFragment)

    @Component.Factory
    interface Factory {
        fun create(settingsDeps: SettingsDeps): SettingsComponent
    }
}