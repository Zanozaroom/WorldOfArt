package com.example.project_alfa_angry_snake.viewmodel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core_api.SharedPrefInterface
import com.example.navigation.NavigationBottomMenuFlow
import com.example.navigation.NavigationFlow
import com.example.navigation.NavigationLoginFlow
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class StartFragmentViewModel @Inject constructor (
    private val sharedPref: SharedPrefInterface,
) : ViewModel() {

    private val _eventNavigation = Channel<NavigationFlow>()
    val eventNavigation = _eventNavigation.receiveAsFlow()

    fun getThemeDayNight(): Flow<Int> = sharedPref.getThemeMode()
    init {
        viewModelScope.launch{
            sharedPref.getIsLogin().collectLatest {isLogin ->
            when(isLogin) {
                true -> _eventNavigation.send(NavigationBottomMenuFlow)
                false -> _eventNavigation.send(NavigationLoginFlow)
            }
            }
        }
    }
}