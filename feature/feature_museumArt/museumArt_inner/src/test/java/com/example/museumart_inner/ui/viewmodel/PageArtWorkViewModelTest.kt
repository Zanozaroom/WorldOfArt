package com.example.museumart_inner.ui.viewmodel

import android.util.Log
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.api_model.pojo.chicagomuseum.CMArtWork
import com.example.api_model.state.UIState
import com.example.museumart_inner.domain.repository.RepositoryChicagoArt
import com.example.museumart_inner.domain.saving.Saver
import com.example.api_model.state.EmptyRepoState
import com.example.api_model.state.ErrorLoadRepoState
import com.example.api_model.state.SuccessRepoState
import io.mockk.*
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.junit4.MockKRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class PageArtWorkViewModelTest{
    @OptIn(ExperimentalCoroutinesApi::class)
    @get:Rule
    val ruleViewModelRules = ViewModelRules()

    @get:Rule
    val ruleInstantTaskExecutorRule = InstantTaskExecutorRule()

    @get: Rule
    val rule = MockKRule(this)

    @RelaxedMockK
    lateinit var repository: RepositoryChicagoArt

    @RelaxedMockK
    lateinit var saver: Saver

    lateinit var viewModelPageArt: PageArtWorkViewModel
    lateinit var artWork: CMArtWork


    @Before
    fun init() {
        mockkStatic(Log::class)
        every { Log.v(any(), any()) } returns 0
        every { Log.d(any(), any()) } returns 0
        every { Log.i(any(), any()) } returns 0
        every { Log.e(any(), any()) } returns 0

        artWork = CMArtWork(
            id = 546,
            title = "Beautiful Rose",
            image = "",
            artist = "unknown Artist",
            dataCreate = "1478",
            placeCreate = "Japan",
            isFavorite = false
        )
    }

    @Test
    fun `loadArtWorkTest GetOneArtFromDB SetSuccessResultFromDataBase`(){
        viewModelPageArt = spyk(PageArtWorkViewModel(repository, saver))
        val stateVMArtWork = UIState.Success(artWork)
        coEvery {
            repository.getOneArtFromDB(any())
        } returns artWork

        viewModelPageArt.loadArtWork(200)

        assertEquals(stateVMArtWork, viewModelPageArt.artWork.value)
        coVerify{
            repository.getOneArtFromDB(200)
        }
    }

    @Test
    fun `loadArtWorkTest GetEmptyFromDataBase LaunchNetworkFun SetSuccessResultFromDataBase`(){
        viewModelPageArt = spyk(PageArtWorkViewModel(repository, saver))
        val stateVM = UIState.Success(artWork)
        coEvery {
            repository.getOneArtFromDB(any())
        } returns null
        coEvery {
            repository.loadOneWork(any())
        } returns SuccessRepoState(artWork)

        viewModelPageArt.loadArtWork(200)

        assertEquals(stateVM, viewModelPageArt.artWork.value)
        coVerify{
            repository.loadOneWork(200)
        }
    }
    @Test
    fun `loadArtWorkTest GetEmptyFromDataBase LaunchNetworkFun SetEmptyStateVM`(){
        viewModelPageArt = spyk(PageArtWorkViewModel(repository, saver))
        val stateVM = UIState.Empty
        coEvery {
            repository.getOneArtFromDB(any())
        } returns null
        coEvery {
            repository.loadOneWork(any())
        } returns EmptyRepoState

        viewModelPageArt.loadArtWork(200)

        assertEquals(stateVM, viewModelPageArt.artWork.value)
        coVerify{
            repository.loadOneWork(200)
        }
    }

    @Test
    fun `loadArtWorkTest GetEmptyFromDataBase LaunchNetworkFun SetErrorStateVM`(){
        viewModelPageArt = spyk(PageArtWorkViewModel(repository, saver))
        val stateVM = UIState.Error
        coEvery {
            repository.getOneArtFromDB(any())
        } returns null
        coEvery {
            repository.loadOneWork(any())
        } returns ErrorLoadRepoState

        viewModelPageArt.loadArtWork(200)

        assertEquals(stateVM, viewModelPageArt.artWork.value)
        coVerify{
            repository.loadOneWork(200)
        }
    }

}