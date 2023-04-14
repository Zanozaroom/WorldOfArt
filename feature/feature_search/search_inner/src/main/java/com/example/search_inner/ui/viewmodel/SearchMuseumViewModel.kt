package com.example.search_inner.ui.viewmodel

import androidx.lifecycle.viewModelScope
import com.example.api_model.pojo.chicagomuseum.CMArtWork
import com.example.api_model.pojo.chicagomuseum.PagingCMArtWork
import com.example.api_model.state.UIState
import com.example.api_model.ui.ButtonsAction
import com.example.search_inner.domain.repository.SearchRepository
import com.example.search_inner.navigator.SearchNavigatorFlow
import com.example.search_inner.ui.utills.OnClickImageSearch
import com.example.api_model.state.EmptyRepoState
import com.example.api_model.state.ErrorLoadRepoState
import com.example.api_model.state.SuccessRepoState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class SearchMuseumViewModel @Inject constructor(
    private val repository: SearchRepository
) : SearchMuseumBaseViewModel(), OnClickImageSearch {
    private var pagingArt = PagingCMArtWork()
    private var query: String = EMPTY_SEARCH


    private val _searchArtWorks = MutableStateFlow<UIState<List<CMArtWork>>>(UIState.Loading)
    val searchArtWorks = _searchArtWorks.asStateFlow()

    init {
        loadSearchArtWork()
    }

    fun setQuery(newQuery: String){
        if(newQuery != query) {
            query = newQuery
            loadSearchArtWork()
        }
        else return
    }

    fun clickOnButtonView(action: ButtonsAction){
        when (action){
            ButtonsAction.ERROR_LOAD -> loadSearchArtWork()
            ButtonsAction.ERROR_LOAD_MORE -> loadSearchMore()
        }
    }

    fun onClickIconClosed(){
        viewModelScope.launch {
            eventNavigationFlow.send(SearchNavigatorFlow.ToBack)
        }
    }

    fun loadSearchArtWork() {
        _searchArtWorks.value = UIState.Loading
        viewModelScope.launch {
            _searchArtWorks.value = when (
                val result = repository.loadSearchArtWorks(query, PagingCMArtWork())
            ) {
                EmptyRepoState -> UIState.Empty
                ErrorLoadRepoState -> UIState.Error
                is SuccessRepoState -> {
                    pagingArt = result.responseData
                    UIState.Success(pagingArt.copy().data)
                }
            }
        }
    }

    fun loadSearchMore() {
        viewModelScope.launch {
            if (pagingArt.nextPage == null) {
                eventMuseumMessageFlow.send(com.example.commonui.R.string.messageAllPageDownLoaded)
                _searchArtWorks.value = UIState.Success(pagingArt.data)
            } else {
                loadMeSearchMore(query)
            }
        }
    }

    private fun loadMeSearchMore(query: String){
        viewModelScope.launch {
            _searchArtWorks.value = when (
                val result = repository.loadSearchArtWorks(query, pagingArt)
            ) {
                is SuccessRepoState ->{
                    pagingArt = result.responseData
                    UIState.Success(pagingArt.copy().data)
                }
                else -> {
                    eventMuseumMessageFlow.send(com.example.api_model.R.string.exceptionNetworkLoadFail)
                    UIState.Error
                }
            }
        }
    }

    override fun onClickImageSearch(itemChicagoMuseumArt: CMArtWork) {
        viewModelScope.launch {
            eventNavigationFlow.send(
                SearchNavigatorFlow.ToDeepLinkOpenPageOfArt(
                    itemChicagoMuseumArt.id.toString()
                )
            )
        }
    }

    companion object {
        const val EMPTY_SEARCH = ""
    }
}
