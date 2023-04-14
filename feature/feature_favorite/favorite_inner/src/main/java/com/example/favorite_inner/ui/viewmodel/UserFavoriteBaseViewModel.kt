package com.example.favorite_inner.ui.viewmodel

import com.example.api_model.ui.BaseViewModel
import com.example.favorite_inner.navigator.FavoriteNavigationFlow
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow

open class UserFavoriteBaseViewModel: BaseViewModel() {
    override val eventNavigationFlow = Channel<FavoriteNavigationFlow>()
    override val observerNavigationFlow = eventNavigationFlow.receiveAsFlow()
}