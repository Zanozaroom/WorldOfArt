package com.example.inner_model

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.api_model.dao.DaoFavoriteArtChicago
import com.example.api_model.pojo.database.EntityArtChicago

@Database(
    entities = [EntityArtChicago::class],
    version = 1,
    exportSchema = true
)
abstract class DataBase: RoomDatabase() {
    abstract fun getDaoArtChicago(): DaoFavoriteArtChicago

}
