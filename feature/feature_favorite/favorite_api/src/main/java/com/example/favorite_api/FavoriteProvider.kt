package com.example.favorite_api

import android.app.Application
import android.content.Context

interface FavoriteProvider {
    val favoriteDeps: FavoriteDeps
}

val Context.favoriteDeps: FavoriteDeps
    get() = when(this){
        is FavoriteDeps -> this
        is FavoriteProvider -> this.favoriteDeps
        is Application -> error("Application must implements FavoriteProvider")
        else -> applicationContext.favoriteDeps
    }
