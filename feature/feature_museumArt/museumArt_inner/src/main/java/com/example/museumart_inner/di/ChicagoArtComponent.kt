package com.example.museumart_inner.di

import com.example.museumart_api.ChicagoMuseumDeps
import com.example.museumart_inner.ui.fragment.MuseumBaseFragment
import dagger.Component
import javax.inject.Qualifier

@ChicagoArtScope
@Component(dependencies = [ChicagoMuseumDeps::class],
    modules = [ChicagoArtModule::class])
interface ChicagoArtComponent {
    fun inject(artWorkFragment: MuseumBaseFragment)

    @Component.Factory
    interface Factory {
        fun create(
            chicagoMuseumShowDependencies: ChicagoMuseumDeps
        ): ChicagoArtComponent
    }
}


@Qualifier
annotation class ChicagoArtScope
