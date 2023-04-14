package com.example.search_inner.di

import com.example.search_api.SearchDeps
import com.example.search_inner.ui.fragment.SearchFragmentBase
import dagger.Component

@Component(
    dependencies = [SearchDeps::class],
    modules = [
        SearchViewModelFactory::class,
        PagingModule::class,
        SearchRepositoryModule::class,
        SearchNavigatorModule::class
    ]
)
interface SearchComponent {
    fun inject(searchFragment: SearchFragmentBase)

    @Component.Factory
    interface Factory {
        fun create(searchDeps: SearchDeps): SearchComponent
    }
}