package com.example.api_model.pojo.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "art_chicago_museum",
    indices = [Index(value = ["id", "id_art"], unique = true)]
)
data class EntityArtChicago(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo(name = "id_art") val idArt: Int,
    val title: String,
    val image: String,
    val artist: String,
    @ColumnInfo(name = "data_create") val dataCreate: String,
    @ColumnInfo(name = "place_create") val placeCreate: String,
)

