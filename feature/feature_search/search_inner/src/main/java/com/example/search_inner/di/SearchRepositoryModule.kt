package com.example.search_inner.di

import com.example.search_inner.domain.repository.SearchRepository
import com.example.search_inner.domain.repository.SearchRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

@Module(includes = [DispatcherModule::class])
interface SearchRepositoryModule {
@Binds
fun bindSearchRepository(searchRepositoryImpl: SearchRepositoryImpl): SearchRepository
}
@Module
object DispatcherModule{
    @Provides
    fun provideDispatcher():CoroutineDispatcher = Dispatchers.IO
}