package com.example.museumart_inner.ui.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.api_model.pojo.chicagomuseum.CMArtWork
import com.example.api_model.pojo.chicagomuseum.PagingCMArtWork
import com.example.api_model.state.UIState
import com.example.museumart_inner.domain.repository.RepositoryChicagoArt
import com.example.api_model.state.EmptyRepoState
import com.example.api_model.state.ErrorLoadRepoState
import com.example.api_model.state.SuccessRepoState
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.junit4.MockKRule
import io.mockk.spyk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class CMArtWorkViewModelTest{
    @OptIn(ExperimentalCoroutinesApi::class)
    @get:Rule
    val ruleViewModelRules = ViewModelRules()

    @get:Rule
    val ruleInstantTaskExecutorRule = InstantTaskExecutorRule()

    @get: Rule
    val rule = MockKRule(this)

    @RelaxedMockK
    lateinit var repository: RepositoryChicagoArt

    lateinit var viewModelHomeArt: MuseumArtWorkViewModel
    private val artWork = CMArtWork(
        id = 256,
        title = "Title Art",
        image = "",
        artist = "Van Gog",
        dataCreate = "1872",
        placeCreate = "France",
        isFavorite = false
    )

    @Before
    fun init() {
        viewModelHomeArt = spyk(MuseumArtWorkViewModel(repository))
    }

    @Test
    fun `loadArtWorkTest getEmptyRepoState set EmptyStateVM`() = runBlocking {
        coEvery {
            repository.loadArtWorks(any())
        } returns EmptyRepoState

        viewModelHomeArt.loadArtWork()
        print(viewModelHomeArt.flowArtWorks.value)
        assertTrue(viewModelHomeArt.flowArtWorks.value is UIState.Empty)
        coVerify {
            repository.loadArtWorks(PagingCMArtWork())
        }
    }

    @Test
    fun `loadArtWorkTest getErrorLoadRepoState set ErrorStateVM`() = runBlocking {
        coEvery {
            repository.loadArtWorks(any())
        } returns ErrorLoadRepoState

        viewModelHomeArt.loadArtWork()
        print(viewModelHomeArt.flowArtWorks.value)
        assertTrue(viewModelHomeArt.flowArtWorks.value is UIState.Error)
        coVerify(exactly = 1) {
            viewModelHomeArt.loadArtWork()
        }
    }

    @Test
    fun `loadArtWorkTest getSuccessErrorRepoState set ErrorStateVM`() = runBlocking {
        coEvery {
            repository.loadArtWorks(any())
        } returns ErrorLoadRepoState

        viewModelHomeArt.loadArtWork()
        print(viewModelHomeArt.flowArtWorks.value)
        assertTrue(viewModelHomeArt.flowArtWorks.value is UIState.Error)
        coVerify(exactly = 1) {
            viewModelHomeArt.loadArtWork()
        }
    }

    @Test
    fun `loadArtWorkTest getSuccessRepoState set SuccessStateVM`() = runBlocking {
        coEvery {
            repository.loadArtWorks(any())
        } returns SuccessRepoState(
            PagingCMArtWork(data = listOf(artWork)))

        viewModelHomeArt.loadArtWork()
        print(viewModelHomeArt.flowArtWorks.value)
        assertEquals(viewModelHomeArt.flowArtWorks.value, UIState.Success(listOf(artWork)))
        coVerify(exactly = 1) {
            viewModelHomeArt.loadArtWork()
        }
    }

}
