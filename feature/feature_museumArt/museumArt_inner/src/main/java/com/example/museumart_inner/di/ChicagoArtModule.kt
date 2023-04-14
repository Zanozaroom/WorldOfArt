package com.example.museumart_inner.di

import dagger.Binds
import dagger.Module
import com.example.museumart_inner.navigation.NavigatorMuseum
import com.example.museumart_inner.navigation.NavigatorMuseumImpl

@Module(
    includes = [
        ChicagoVMFactory::class,
        SaverModule::class,
        PagingModule::class,
        RepositoryModule::class,
        DispatchersModule::class
    ]
)
interface ChicagoArtModule{
    @Binds
    fun bindNavigator(navigatorMuseumImpl: NavigatorMuseumImpl): NavigatorMuseum
}

