package com.example.core_di

import android.content.Context
import android.content.SharedPreferences
import com.example.core_api.SharedPrefInterface
import com.example.core_inner.preference.SharedPrefImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [BindPreference::class])
object SharedPreferenceModule {
    @Singleton
    @Provides
    fun provideSharedPreference(context: Context): SharedPreferences{
       return context.getSharedPreferences(NAME_PREF_FILE, Context.MODE_PRIVATE)
    }
    private const val NAME_PREF_FILE = "mysettings"
}
@Module
interface BindPreference{
    @Binds
    @Singleton
    fun bindSharedPreferenceImpl(sharedPrefImpl: SharedPrefImpl): SharedPrefInterface
}