//package com.example.project_alfa_angry_snake.di
//
//import com.example.api_model.dao.DaoFavoriteArtChicago
//import com.example.api_model.retrofit.ChicagoMuseumRetrofit
//import com.example.core_api.LoggerApp
//import com.example.core_di.LoggerModule
//import com.example.core_di.SharedPreferenceModule
//import com.example.core_di.StreamModule
//import com.example.core_inner.logger.LoggerAppImpl
//import com.example.project_alfa_angry_snake.di.service.NavigatorModule
//import com.example.project_alfa_angry_snake.testclasses.RetrofitResponseFake
//import com.google.firebase.auth.FirebaseAuth
//import dagger.Binds
//import dagger.Module
//import dagger.Provides
//import io.mockk.mockk
//
//import javax.inject.Singleton
//
//@Module(
//    includes = [
//        NetworkBaseModuleTest::class,
//        ChicagoRetrofitModuleTest::class,
//        DataBaseModule::class,
//        FirebaseAuthModule::class,
//        com.example.inner_model.di.database.MapperDataBaseModule::class,
//        SharedPreferenceModule::class,
//        StreamModule::class,
//        NavigatorModule::class,
//        com.example.inner_model.di.retrofit.MapperChicagoRetrofitModule::class,
//        LoggerModule::class,
//        com.example.inner_model.di.retrofit.CreatorFieldsModule::class
//    ]
//)
//interface AppModuleTest
//
//@Module
//object NetworkBaseModuleTest
//
//@Module
//interface ChicagoRetrofitModuleTest {
//    @Binds
//    @Singleton
//    fun provideChicagoApiClient(
//        retrofitResponseFake:
//        RetrofitResponseFake
//    ): ChicagoMuseumRetrofit
//}
//
//@Module
//object DataBaseModule {
//    @Singleton
//    @Provides
//    fun provideDaoArtChicago(): DaoFavoriteArtChicago = mockk<DaoFavoriteArtChicago>(relaxed = true)
//}
//
//@Module
//object FirebaseAuthModule {
//    @Provides
//    @Singleton
//    fun provideFireAuthClient(): FirebaseAuth = mockk<FirebaseAuth>(relaxed = true)
//}
//@Module
//interface LoggerModule {
//    @Provides
//    @Singleton
//    fun bindLogger(loggerAppImpl: LoggerAppImpl)= mockk<LoggerApp>(relaxed = true)
//}