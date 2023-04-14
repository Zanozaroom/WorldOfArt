package com.example.search_api

import android.app.Application
import android.content.Context

interface SearchProvider {
    val searchDeps: SearchDeps
}
val Context.searchDeps: SearchDeps
    get() = when(this){
        is SearchDeps -> this
        is SearchProvider -> this.searchDeps
        is Application -> error("Application must implements SearchProvider")
        else -> applicationContext.searchDeps
    }