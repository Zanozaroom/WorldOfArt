package com.example.project_alfa_angry_snake.di.service

import com.example.navigation.MainNavigatorCreator
import com.example.navigation.NavigatorI
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object NavigatorModule {
    @Provides
    @Singleton
    fun provideModule(): NavigatorI {
        return MainNavigatorCreator.getNav()
    }
}