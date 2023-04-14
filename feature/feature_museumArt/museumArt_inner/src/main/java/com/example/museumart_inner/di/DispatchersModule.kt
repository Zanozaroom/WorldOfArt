package com.example.museumart_inner.di

import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import com.example.museumart_inner.navigation.NavigatorMuseumImpl

@Module
object DispatchersModule {
    @Provides
    fun provideDispatchersIO(): CoroutineDispatcher = Dispatchers.IO
}
