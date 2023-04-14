package com.example.base_test

import android.content.Context
import com.example.project_alfa_angry_snake.di.App.AppComponent
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModuleTestBase::class])
interface AppComponentTestBase: AppComponent {
    fun inject(app: AppTestBase)

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance appContext: Context): AppComponentTestBase
    }
}