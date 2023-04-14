package com.example.setting_inner.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.core_di.ViewModelFactory
import com.example.setting_inner.ui.viewmodel.SettingViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface SettingVMFactory {
    @Binds
    fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelFactory.ViewModelKey(SettingViewModel::class)
    fun provideSingUpViewModel(viewModel: SettingViewModel): ViewModel
}