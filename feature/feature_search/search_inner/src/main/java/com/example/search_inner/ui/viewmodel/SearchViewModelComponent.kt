package com.example.search_inner.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.search_api.searchDeps
import com.example.search_inner.di.DaggerSearchComponent
import com.example.search_inner.di.SearchComponent

class SearchViewModelComponent(application: Application): AndroidViewModel(application) {
        val searchComponent: SearchComponent by lazy {
            DaggerSearchComponent.factory().create(application.searchDeps)
        }
}