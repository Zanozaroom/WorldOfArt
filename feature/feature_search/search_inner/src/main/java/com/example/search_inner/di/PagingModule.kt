package com.example.search_inner.di

import com.example.search_inner.domain.paging.CreatorPaging
import com.example.search_inner.domain.paging.CreatorPagingImpl
import dagger.Binds
import dagger.Module

@Module
interface PagingModule {
    @Binds
    fun bindPaging(pagingImpl: CreatorPagingImpl): CreatorPaging
}