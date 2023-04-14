package com.example.inner_model.di.database

import com.example.api_model.mapper.MapperDataBase
import com.example.inner_model.mapper.MapperDataBaseImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface MapperDataBaseModule {
    @Binds
    @Singleton
    fun bindMapperDataBase(mapperDataBaseImpl: MapperDataBaseImpl): MapperDataBase
}