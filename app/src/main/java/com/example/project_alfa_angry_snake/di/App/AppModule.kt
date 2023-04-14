package com.example.project_alfa_angry_snake.di.App

import com.example.auth_api.FirebaseAuthModule
import com.example.core_di.LoggerModule
import com.example.core_di.SharedPreferenceModule
import com.example.core_di.StreamModule
import com.example.project_alfa_angry_snake.di.service.NavigatorModule
import com.example.inner_model.di.database.DataBaseModule
import com.example.inner_model.di.retrofit.ChicagoRetrofitModule
import com.example.inner_model.di.retrofit.CreatorFieldsModule
import com.example.inner_model.di.retrofit.MapperChicagoRetrofitModule
import com.example.network_api.NetworkBaseModule
import dagger.Module

@Module(includes = [
    NetworkBaseModule::class,
    ChicagoRetrofitModule::class,
    DataBaseModule::class,
    SharedPreferenceModule::class,
    StreamModule::class,
    NavigatorModule::class,
    MapperChicagoRetrofitModule::class,
    FirebaseAuthModule::class,
    LoggerModule::class,
    CreatorFieldsModule::class
    ])
interface AppModule


