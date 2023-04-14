package com.example.museumart_inner.di

import com.example.museumart_inner.domain.paging.CreatorPaging
import com.example.museumart_inner.domain.paging.CreatorPagingImpl
import dagger.Binds
import dagger.Module

@Module
interface PagingModule {
    @Binds
    fun bindPaging(creatorPaging: CreatorPagingImpl): CreatorPaging
}