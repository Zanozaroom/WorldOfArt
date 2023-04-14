package com.example.project_alfa_angry_snake

import android.app.Application
import com.example.project_alfa_angry_snake.di.App.AppComponent
import com.example.project_alfa_angry_snake.di.App.DaggerAppComponent
import com.example.project_alfa_angry_snake.di.ui.activity.ActivityDepsProvider
import com.example.auth_api.LoginProvider
import com.example.favorite_api.FavoriteProvider
import com.example.museumart_api.ChicagoArtProvider
import com.example.search_api.SearchProvider
import com.example.setting_api.SettingsProvider

open class App:
    Application(),
    ChicagoArtProvider,
    ActivityDepsProvider,
    FavoriteProvider,
    SearchProvider,
    LoginProvider,
    SettingsProvider
{
    protected var appComponent: AppComponent? = null

    override fun onCreate() {
        super.onCreate()
        appComponent = getComponent()
        appComponent!!.inject(this)
    }

    protected open fun getComponent() = DaggerAppComponent
        .factory()
        .create(this)

    override val chicagoMuseumDeps = appComponent ?: getComponent()
    override val activityDeps = appComponent ?: getComponent()
    override val favoriteDeps = appComponent ?: getComponent()
    override val searchDeps = appComponent ?: getComponent()
    override val loginDeps = appComponent ?: getComponent()
    override val settingsDeps = appComponent ?: getComponent()
}
