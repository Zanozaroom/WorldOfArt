package com.example.api_model.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.api_model.pojo.database.EntityArtChicago
import kotlinx.coroutines.flow.Flow
@Dao
interface DaoFavoriteArtChicago {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addToFavoriteChicagoArt(entityArtChicago: EntityArtChicago)

    @Query("SELECT * FROM art_chicago_museum")
    fun loadFavoriteChicagoArt(): Flow<List<EntityArtChicago>>

    @Query("DELETE FROM art_chicago_museum WHERE id_art = :idArtChicagoMuseum")
    suspend fun deleteChicagoArtFromFavorite(idArtChicagoMuseum: Int)

    @Query("SELECT * FROM art_chicago_museum WHERE id_art = :idArt")
    suspend fun loadOneArtWork(idArt: Int): EntityArtChicago?
}
