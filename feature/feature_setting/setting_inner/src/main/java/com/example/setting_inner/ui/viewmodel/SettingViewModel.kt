package com.example.setting_inner.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core_api.SharedPrefInterface
import com.example.navigation.NavigationFlow
import com.example.navigation.NavigationLoginFlow
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class SettingViewModel @Inject constructor(
    private val sharedPref: SharedPrefInterface): ViewModel() {
    private val eventNavigationFlow = Channel<NavigationFlow>()
    val observerMuseumNavigationFlow = eventNavigationFlow.receiveAsFlow()

    fun logOutUser(){
        viewModelScope.launch {
            sharedPref.editIsLogin(false)
            eventNavigationFlow.send(NavigationLoginFlow)
        }
    }
    fun editThemeDayNight(dayNightMode: Int) {
        viewModelScope.launch {
            sharedPref.editThemeMode(dayNightMode)
        }
    }
}