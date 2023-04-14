package com.example.core_di

import com.example.core_api.ImageInputStream
import com.example.core_api.ImageOutputStream
import com.example.core_inner.streams.ImageInputStreamImpl
import com.example.core_inner.streams.ImageOutputStreamImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

@Module(includes = [DispatcherModule::class])
interface StreamModule {
    @Binds
    fun bindImageInputStream(imageStream: ImageInputStreamImpl): ImageInputStream
    @Binds
    fun bindImageOutputStream(imageStream: ImageOutputStreamImpl): ImageOutputStream
}
@Module
object DispatcherModule {
    @Provides
    fun provideDispatcher(): CoroutineDispatcher = Dispatchers.IO
}