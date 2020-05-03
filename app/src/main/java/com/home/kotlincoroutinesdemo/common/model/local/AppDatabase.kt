package com.home.kotlincoroutinesdemo.common.model.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.home.kotlincoroutinesdemo.albums.model.local.AlbumsDao
import com.home.kotlincoroutinesdemo.albums.model.local.AlbumsEntity

@Database(entities = [AlbumsEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun albumsDao(): AlbumsDao
}