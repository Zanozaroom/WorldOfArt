package com.example.network_api


import com.example.network.base.CustomResponseAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.Protocol
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.CallAdapter

@Module
object NetworkBaseModule {
    @Provides
    fun provideHttpClient(interceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .protocols(mutableListOf(Protocol.HTTP_1_1)).build()
    }
    @Provides
    fun provideInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }
    @Provides
    fun provideCustomFactory(): CallAdapter.Factory = CustomResponseAdapterFactory()

}