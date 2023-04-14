package com.example.search_inner.di

import com.example.search_inner.navigator.SearchNavigator
import com.example.search_inner.navigator.SearchNavigatorImpl
import dagger.Binds
import dagger.Module

@Module
interface SearchNavigatorModule {
    @Binds
    fun bindNavigator(searchNavigatorImpl: SearchNavigatorImpl): SearchNavigator
}