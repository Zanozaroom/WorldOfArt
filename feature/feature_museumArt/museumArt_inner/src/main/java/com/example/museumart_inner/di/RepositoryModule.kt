package com.example.museumart_inner.di

import com.example.museumart_inner.domain.repository.RepositoryChicagoArt
import com.example.museumart_inner.domain.repository.RepositoryChicagoArtImpl
import dagger.Binds
import dagger.Module

@Module
interface RepositoryModule {
    @Binds
    fun bindRepository(repositoryArt: RepositoryChicagoArtImpl): RepositoryChicagoArt
}