package com.example.museumart_inner.di

import com.example.museumart_inner.domain.saving.Saver
import com.example.museumart_inner.domain.saving.SaverImpl
import dagger.Binds
import dagger.Module

@Module
interface SaverModule {
    @Binds
    fun bindSaver(server: SaverImpl): Saver
}