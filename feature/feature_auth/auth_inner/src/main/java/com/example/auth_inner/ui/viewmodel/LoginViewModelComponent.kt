package com.example.auth_inner.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.auth_api.loginDeps
import com.example.auth_inner.di.DaggerLoginComponent
import com.example.auth_inner.di.LoginComponent

class LoginViewModelComponent(application: Application): AndroidViewModel(application) {
    val loginComponent: LoginComponent by lazy {
        DaggerLoginComponent.factory().create(application.loginDeps)
    }
}