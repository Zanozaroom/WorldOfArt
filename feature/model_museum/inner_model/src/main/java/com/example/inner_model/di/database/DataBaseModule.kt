package com.example.inner_model.di.database

import android.content.Context
import androidx.room.Room
import com.example.api_model.dao.DaoFavoriteArtChicago
import com.example.inner_model.DataBase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [MapperDataBaseModule::class])
object DataBaseModule {
    @Singleton
    @Provides
    fun provideDataBase(context: Context): DataBase {
        return Room
            .databaseBuilder(context, DataBase::class.java, NAME_DATA_BASE)
            .build()
    }
    @Singleton
    @Provides
    fun provideDaoArtChicago(db: DataBase): DaoFavoriteArtChicago = db.getDaoArtChicago()

    private const val NAME_DATA_BASE = "artDataBase"
}
