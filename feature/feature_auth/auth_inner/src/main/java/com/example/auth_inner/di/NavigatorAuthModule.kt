package com.example.auth_inner.di

import com.example.auth_inner.navigation.NavigatorAuth
import com.example.auth_inner.navigation.NavigatorAuthImpl
import dagger.Binds
import dagger.Module

@Module
interface NavigatorAuthModule {
@Binds
fun bindNavAuth(navigatorAuth: NavigatorAuthImpl): NavigatorAuth
}
