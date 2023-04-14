package com.example.favorite_inner.di


import com.example.favorite_api.FavoriteDeps
import com.example.favorite_inner.ui.fragment.FavoriteArtFragment
import com.example.favorite_inner.ui.fragment.FavoriteArtFragmentBase
import dagger.Component
import javax.inject.Qualifier

@FavoriteScope
@Component(
    dependencies = [FavoriteDeps::class],
    modules = [
        FavoriteVMFactory::class,
        RepositoryModule::class,
        DispatchersModule::class,
        FavoriteNavigatorModule::class
    ]
)
interface FavoriteComponent {
    fun inject(artWorkFragment: FavoriteArtFragmentBase)

    @Component.Factory
    interface Factory {
        fun create(
            chicagoMuseumShowDependencies: FavoriteDeps
        ): FavoriteComponent
    }
}

@Qualifier
annotation class FavoriteScope
