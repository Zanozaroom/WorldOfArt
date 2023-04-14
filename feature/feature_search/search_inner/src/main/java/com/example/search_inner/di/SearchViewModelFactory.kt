package com.example.search_inner.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.core_di.ViewModelFactory
import com.example.search_inner.ui.viewmodel.SearchMuseumViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface SearchViewModelFactory {
    @Binds
    fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
    @Binds
    @IntoMap
    @ViewModelFactory.ViewModelKey(SearchMuseumViewModel::class)
    fun provideSearchMuseumViewModel(viewModel: SearchMuseumViewModel): ViewModel
}