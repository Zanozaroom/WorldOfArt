package com.example.inner_model.di.retrofit

import com.example.api_model.mapper.MapperPojoChicagoRetrofit
import com.example.inner_model.mapper.MapperChicagoRetrofitImpl
import dagger.Binds
import dagger.Module

@Module
interface MapperChicagoRetrofitModule {
    @Binds
    fun bindMapperChicagoPojo(mapperImpl: MapperChicagoRetrofitImpl): MapperPojoChicagoRetrofit
}