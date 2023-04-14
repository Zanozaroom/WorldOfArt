package com.example.project_alfa_angry_snake.di.ui.activity

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.core_di.ViewModelFactory
import com.example.project_alfa_angry_snake.viewmodel.StartFragmentViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ActivityVMFactory {
    @Binds
    fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
    @Binds
    @IntoMap
    @ViewModelFactory.ViewModelKey(StartFragmentViewModel::class)
    fun provideSettingsViewModel(viewModel: StartFragmentViewModel): ViewModel
}