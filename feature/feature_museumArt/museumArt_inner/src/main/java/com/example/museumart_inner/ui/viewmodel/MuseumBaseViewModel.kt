package com.example.museumart_inner.ui.viewmodel

import com.example.api_model.ui.BaseViewModel
import kotlinx.coroutines.channels.Channel
import com.example.museumart_inner.navigation.FlowMuseumNav
import kotlinx.coroutines.flow.receiveAsFlow

open class MuseumBaseViewModel: BaseViewModel() {
    override val eventNavigationFlow = Channel<FlowMuseumNav>()
    override val observerNavigationFlow = eventNavigationFlow.receiveAsFlow()
}