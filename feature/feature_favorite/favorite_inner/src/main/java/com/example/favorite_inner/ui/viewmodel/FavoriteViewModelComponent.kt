package com.example.favorite_inner.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.favorite_api.favoriteDeps
import com.example.favorite_inner.di.DaggerFavoriteComponent
import com.example.favorite_inner.di.FavoriteComponent

class FavoriteViewModelComponent(application: Application): AndroidViewModel(application) {
    val favoriteComponent: FavoriteComponent by lazy {
        DaggerFavoriteComponent.factory().create(application.favoriteDeps)
    }
}
