package com.example.auth_inner.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.auth_inner.ui.viewmodel.LoginViewModel
import com.example.core_di.ViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface LoginVMFactory {
        @Binds
        fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

        @Binds
        @IntoMap
        @ViewModelFactory.ViewModelKey(LoginViewModel::class)
        fun provideSingUpViewModel(viewModel: LoginViewModel): ViewModel
}
