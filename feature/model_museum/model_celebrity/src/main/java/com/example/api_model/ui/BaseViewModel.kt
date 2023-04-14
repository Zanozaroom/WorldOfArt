package com.example.api_model.ui

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow

abstract class BaseViewModel : ViewModel() {
    abstract protected val eventNavigationFlow:Channel<*>
    abstract val observerNavigationFlow: Flow<*>

    protected val eventMessageFlow = Channel<Int>()
    val observerMessageFlow = eventMessageFlow.receiveAsFlow()
}
