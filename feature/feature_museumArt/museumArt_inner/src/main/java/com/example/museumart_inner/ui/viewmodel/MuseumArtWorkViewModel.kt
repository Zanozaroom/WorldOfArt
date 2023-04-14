package com.example.museumart_inner.ui.viewmodel

import androidx.lifecycle.viewModelScope
import com.example.api_model.pojo.chicagomuseum.CMArtWork
import com.example.api_model.pojo.chicagomuseum.PagingCMArtWork
import com.example.api_model.state.UIState
import com.example.museumart_inner.R
import com.example.museumart_inner.domain.repository.RepositoryChicagoArt
import com.example.api_model.state.EmptyRepoState
import com.example.api_model.state.ErrorLoadRepoState
import com.example.api_model.state.SuccessRepoState
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import com.example.museumart_inner.navigation.FlowMuseumNav
import com.example.api_model.click.OnClickImageOnAdapterArtWork
import javax.inject.Inject

class MuseumArtWorkViewModel @Inject constructor(
    private val repository: RepositoryChicagoArt
) : MuseumBaseViewModel(), OnClickImageOnAdapterArtWork {
    private var pagingArt = PagingCMArtWork()

    private val _flowArtWorks = MutableStateFlow<UIState<List<CMArtWork>>>(UIState.Loading)
    val flowArtWorks = _flowArtWorks.asStateFlow()

    init {
        viewModelScope.launch {
            loadArtWork()
        }
    }

    fun clickOnMenuToolbar(id: Int) {
        if (id == R.id.search) {
            viewModelScope.launch {
                eventNavigationFlow.send(FlowMuseumNav.ToSearch)
            }
        }
    }

    fun loadArtWork() {
        viewModelScope.launch {
            when (val result = repository.loadArtWorks(pagingArt)) {
                is EmptyRepoState -> _flowArtWorks.value = UIState.Empty
                is ErrorLoadRepoState -> _flowArtWorks.value = UIState.Error
                is SuccessRepoState -> {
                    pagingArt = result.responseData
                    _flowArtWorks.value = UIState.Success(pagingArt.data)
                }
            }
        }
    }

    fun loadMore() {
        viewModelScope.launch {
            if (pagingArt.nextPage == null) {
                eventMessageFlow.send(com.example.commonui.R.string.messageAllPageDownLoaded)
            } else {
                viewModelScope.launch {
                    loadMoreStart()
                }
            }
        }
    }

    private fun loadMoreStart(){
        viewModelScope.launch {
            when (val result = repository.loadArtWorks(pagingArt)) {
                is SuccessRepoState -> {
                    pagingArt = result.responseData
                    _flowArtWorks.value = UIState.Success(pagingArt.data)
                }
                else -> {
                    _flowArtWorks.value = UIState.ErrorLoadMore
                    eventMessageFlow.send(com.example.api_model.R.string.exceptionNetworkLoadFail)
                }
            }
        }
    }

    override fun onClickOnImage(item: CMArtWork) {
        viewModelScope.launch {
            eventNavigationFlow.send(FlowMuseumNav.ToArtPage(item.id.toString()))
        }
    }
}



