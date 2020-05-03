package com.home.kotlincoroutinesdemo.albums.model.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface AlbumsDao {

    @Query("SELECT * FROM albumsentity")
    suspend fun getAll(): List<AlbumsEntity>

    @Insert
    suspend fun insertAll(users: List<AlbumsEntity>)

    @Delete
    suspend fun delete(user: AlbumsEntity)

}