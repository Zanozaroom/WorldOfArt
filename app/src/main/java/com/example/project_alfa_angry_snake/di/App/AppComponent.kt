package com.example.project_alfa_angry_snake.di.App

import android.content.Context
import com.example.api_model.dao.DaoFavoriteArtChicago
import com.example.api_model.mapper.MapperDataBase
import com.example.api_model.mapper.MapperPojoChicagoRetrofit
import com.example.api_model.retrofit.ChicagoMuseumRetrofit
import com.example.api_model.retrofit.CreatorFields
import com.example.auth_api.LoginDeps
import com.example.core_api.ImageInputStream
import com.example.core_api.ImageOutputStream
import com.example.core_api.LoggerApp
import com.example.core_api.SharedPrefInterface
import com.example.favorite_api.FavoriteDeps
import com.example.museumart_api.ChicagoMuseumDeps
import com.example.navigation.Navigator
import com.example.navigation.NavigatorI
import com.example.project_alfa_angry_snake.App
import com.example.project_alfa_angry_snake.di.ui.activity.ActivityDeps
import com.example.search_api.SearchDeps
import com.example.setting_api.SettingsDeps
import com.google.firebase.auth.FirebaseAuth
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Component(modules = [AppModule::class])
@Singleton
interface AppComponent :
    ChicagoMuseumDeps,
    FavoriteDeps,
    SearchDeps,
    ActivityDeps,
    LoginDeps,
    SettingsDeps
{
    override val retrofitChicago: ChicagoMuseumRetrofit
    override val dataBaseFavorite: DaoFavoriteArtChicago
    override val mapperPojoChicagoRetrofit: MapperPojoChicagoRetrofit
    override val mapperDataBase: MapperDataBase
    override val inputImageStreamer: ImageInputStream
    override val outputImageStreamer: ImageOutputStream
    override val firebaseAuth: FirebaseAuth
    override val sharedPref: SharedPrefInterface
    override val navigator: NavigatorI
    override val logger: LoggerApp
    override val creatorFields: CreatorFields

    fun inject(app: App)
    val getAppContext: Context

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance appContext: Context): AppComponent
    }
}

