package com.example.favorite_inner.di

import com.example.favorite_inner.navigator.FavoriteNavigator
import com.example.favorite_inner.navigator.FavoriteNavigatorImpl
import dagger.Binds
import dagger.Module

@Module
interface FavoriteNavigatorModule {
    @Binds
    fun bindNavigator(favoriteNavigatorImpl: FavoriteNavigatorImpl): FavoriteNavigator
}