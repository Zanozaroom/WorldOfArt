package com.example.auth_inner.di

import com.example.auth_api.LoginDeps
import com.example.auth_inner.ui.fragment.BaseAuthFragment
import com.example.auth_inner.ui.fragment.SingInFragment
import com.example.auth_inner.ui.fragment.SingUpFragment
import dagger.Component
import dagger.assisted.AssistedFactory

@Component(dependencies = [LoginDeps::class], modules = [LoginModule::class])
interface LoginComponent {
    fun inject(fragment: BaseAuthFragment)

    @Component.Factory
    interface Factory {
        fun create(loginDeps: LoginDeps): LoginComponent
    }

}
