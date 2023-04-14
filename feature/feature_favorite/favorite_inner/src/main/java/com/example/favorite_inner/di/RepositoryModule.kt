package com.example.favorite_inner.di

import com.example.favorite_inner.domain.RepositoryFavorite
import com.example.favorite_inner.domain.RepositoryFavoriteImpl
import dagger.Binds
import dagger.Module

@Module
interface RepositoryModule {
    @Binds
    fun bindRepository(repositoryFavoriteImpl: RepositoryFavoriteImpl): RepositoryFavorite
}