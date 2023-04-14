package com.example.search_inner.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.example.search_inner.navigator.SearchNavigatorFlow
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow

open class SearchMuseumBaseViewModel: ViewModel() {
    protected val eventNavigationFlow = Channel<SearchNavigatorFlow>()
    val observerMuseumNavigationFlow = eventNavigationFlow.receiveAsFlow()

    protected val eventMuseumMessageFlow = Channel<Int>()
    val observerMuseumMessageFlow = eventMuseumMessageFlow.receiveAsFlow()
}