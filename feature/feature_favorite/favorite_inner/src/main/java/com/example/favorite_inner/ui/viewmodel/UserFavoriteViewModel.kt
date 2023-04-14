package com.example.favorite_inner.ui.viewmodel

import androidx.lifecycle.viewModelScope
import com.example.api_model.pojo.chicagomuseum.CMArtWork
import com.example.api_model.state.UIState
import com.example.favorite_inner.domain.RepositoryFavorite
import com.example.favorite_inner.navigator.FavoriteNavigationFlow
import com.example.api_model.click.OnClickImageOnAdapterArtWork
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

class UserFavoriteViewModel @Inject constructor (
    private val repository: RepositoryFavorite
) : UserFavoriteBaseViewModel(), OnClickImageOnAdapterArtWork {

    fun loadFavorite():Flow<UIState<List<CMArtWork>>> {
           return repository.getFromDB()
                .catch {
                    UIState.Error
                  eventMessageFlow.send(com.example.api_model.R.string.exceptionDataBaseLoadFail)
                }
                .map {
                    if(it.isEmpty())  UIState.Empty
                    else UIState.Success(it)
                }
    }

    fun deleteFromFavoriteList(artWork: CMArtWork) {
        viewModelScope.launch {
                when(repository.deleteFromDB(artWork.id)){
                  true -> eventMessageFlow.send(com.example.api_model.R.string.toastDeleteArt)
                    false -> eventMessageFlow.send(com.example.api_model.R.string.exceptionDeleteFromDataBase)
                }
        }

    }

    override fun onClickOnImage(item: CMArtWork) {
        viewModelScope.launch{
            eventNavigationFlow.send(FavoriteNavigationFlow.ToDeepLinkPageOfArt(item.id.toString()))
        }
    }
}



