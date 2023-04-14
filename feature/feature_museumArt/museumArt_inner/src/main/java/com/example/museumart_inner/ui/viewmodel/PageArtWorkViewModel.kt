package com.example.museumart_inner.ui.viewmodel

import androidx.lifecycle.viewModelScope
import com.example.api_model.pojo.chicagomuseum.CMArtWork
import com.example.api_model.state.UIState
import com.example.museumart_inner.domain.repository.RepositoryChicagoArt
import com.example.museumart_inner.domain.saving.Saver
import com.example.museumart_inner.ui.ActionPageOfArt
import com.example.api_model.state.EmptyRepoState
import com.example.api_model.state.SuccessRepoState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class PageArtWorkViewModel @Inject constructor(
    private val repository: RepositoryChicagoArt,
    private val saver: Saver
) : MuseumBaseViewModel() {
    private var art: CMArtWork? = null
    private val _artWork = MutableStateFlow<UIState<CMArtWork>>(UIState.Loading)
    val artWork = _artWork.asStateFlow()

    private val _flowSaveData = Channel<String> ()
    val flowSaveData = _flowSaveData.receiveAsFlow()

    fun loadArtWork(idArt: Int) {
        if (art != null) _artWork.value = UIState.Success(art!!)
        else {
            _artWork.value = UIState.Loading
            viewModelScope.launch {
                val resultDB = tryLoadFromDataBase(idArt)
                if (!resultDB) tryLoadFromNetwork(idArt)
            }
        }
    }

    fun onClickIconButtonView(action: ActionPageOfArt){
        viewModelScope.launch {
            when(action){
                ActionPageOfArt.DOWN_LOAD -> _flowSaveData.send(setDataForStartResultActivity())
                ActionPageOfArt.IS_FAVORITE -> clickOnIconFavorite()
            }
        }
    }

    fun saveImage(uri: String) {
        viewModelScope.launch {
            when(saver.saveInPhone(art!!.image, uri)){
                true -> eventMessageFlow.send(com.example.api_model.R.string.toastSavedArt)
                false -> eventMessageFlow.send(com.example.api_model.R.string.exceptionNetworkLoadFail)
            }
        }
    }

    private suspend fun tryLoadFromDataBase(idArt: Int): Boolean {
        val result = repository.getOneArtFromDB(idArt)
        return if (result == null) false
        else {
            art = result
            _artWork.value = UIState.Success(art!!)
            true
        }
    }

    private suspend fun clickOnIconFavorite() {
        when (art!!.isFavorite) {
            true -> deleteFromFavorite(art!!.id)
            false -> addToFavorite(art!!)
        }
    }

    private fun setDataForStartResultActivity(): String {
        val sdf = SimpleDateFormat("dMMyHmmss")
        val currentDateAndTime = sdf.format(Date())
        return "$currentDateAndTime$END_IMAGE_NAME"
    }

    private suspend fun tryLoadFromNetwork(idArt: Int) {
        when (val resultNet = repository.loadOneWork(idArt)) {
            is EmptyRepoState -> _artWork.value = UIState.Empty
            is SuccessRepoState -> {
                art = resultNet.responseData
                _artWork.value = UIState.Success(art!!)
            }
            else -> {
                _artWork.value = UIState.Error
                eventMessageFlow.send(com.example.api_model.R.string.exceptionNetworkLoadFail)
            }
        }
    }

    private suspend fun deleteFromFavorite(id: Int) {
            when (repository.deleteFromDB(id)) {
                true -> {
                    art = art!!.copy(isFavorite = false)
                    _artWork.value = UIState.Success(art!!)
                    eventMessageFlow.send(com.example.api_model.R.string.toastDeleteArt)
                }
                false -> eventMessageFlow.send(com.example.api_model.R.string.exceptionDeleteFromDataBase)
            }
        }


    private suspend fun addToFavorite(artWork: CMArtWork) {
            when (repository.addToDB(artWork)){
                true -> {
                    art = art!!.copy(isFavorite = true)
                    _artWork.value = UIState.Success(art!!)
                    eventMessageFlow.send(com.example.api_model.R.string.toastAddArt)
                }
                false -> eventMessageFlow.send(com.example.api_model.R.string.exceptionAddToDataBaseFail)
            }
    }

    companion object {
        const val MIME_TYPE = "image/jpg"
        private const val END_IMAGE_NAME = "_art.jpg"
    }
}






