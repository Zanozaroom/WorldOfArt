package com.example.favorite_inner.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.core_di.ViewModelFactory
import com.example.favorite_inner.ui.viewmodel.UserFavoriteViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface FavoriteVMFactory {
    @Binds
    fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
    @Binds
    @IntoMap
    @ViewModelFactory.ViewModelKey(UserFavoriteViewModel::class)
    fun provideCMArtWorkViewModel(viewModel: UserFavoriteViewModel): ViewModel
}
