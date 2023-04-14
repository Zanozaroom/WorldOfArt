package com.example.inner_model.di.retrofit

import com.example.api_model.retrofit.ChicagoMuseumRetrofit
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.CallAdapter
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
object ChicagoRetrofitModule {
    @Provides
    @Singleton
    fun provideMoshi(): Moshi = Moshi.Builder().build()

    @Provides
    @Singleton
    fun provideretrofit(
        customResponseAdapterFactory: CallAdapter.Factory,
        moshi: Moshi,
        client: OkHttpClient
    ): Retrofit {
        return  Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addCallAdapterFactory(customResponseAdapterFactory)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(client)
            .build()
    }

    @Provides
    @Singleton
    fun provideChicagoApiClient(
        retrofit: Retrofit
    ): ChicagoMuseumRetrofit = retrofit.create(ChicagoMuseumRetrofit::class.java)



    private const val BASE_URL = "https://api.artic.edu/api/v1/"
}
