package com.example.favorite_inner.di

import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

@Module
object DispatchersModule {
    @Provides
    fun provideDispatchersIO(): CoroutineDispatcher = Dispatchers.IO
}
