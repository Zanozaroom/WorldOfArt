package com.example.inner_model.di.retrofit

import com.example.api_model.retrofit.CreatorFields
import com.example.inner_model.repository.CreatorFieldsImpl
import dagger.Binds
import dagger.Module

@Module
interface CreatorFieldsModule {
@Binds
fun bindCreatorFields(creatorFieldsImpl: CreatorFieldsImpl): CreatorFields
}