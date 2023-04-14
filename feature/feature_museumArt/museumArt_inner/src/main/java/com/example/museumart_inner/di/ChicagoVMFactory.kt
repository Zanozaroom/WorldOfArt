package com.example.museumart_inner.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.core_di.ViewModelFactory
import com.example.museumart_inner.ui.viewmodel.MuseumArtWorkViewModel
import com.example.museumart_inner.ui.viewmodel.PageArtWorkViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ChicagoVMFactory {
    @Binds
    fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
    @Binds
    @IntoMap
    @ViewModelFactory.ViewModelKey(MuseumArtWorkViewModel::class)
    fun provideCMArtWorkViewModel(viewModel: MuseumArtWorkViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelFactory.ViewModelKey(PageArtWorkViewModel::class)
    fun providePageOneArtViewModel(viewModel: PageArtWorkViewModel): ViewModel
}
