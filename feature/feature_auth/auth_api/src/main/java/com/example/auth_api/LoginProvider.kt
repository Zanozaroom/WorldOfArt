package com.example.auth_api

import android.app.Application
import android.content.Context

interface LoginProvider {
    val loginDeps: LoginDeps
}
val Context.loginDeps: LoginDeps
    get() = when(this){
        is LoginDeps -> this
        is LoginProvider -> this.loginDeps
        is Application -> error("Application must implements LoginProvider")
        else -> applicationContext.loginDeps
    }
