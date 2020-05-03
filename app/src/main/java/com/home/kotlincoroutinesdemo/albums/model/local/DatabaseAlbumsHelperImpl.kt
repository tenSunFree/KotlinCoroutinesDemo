package com.home.kotlincoroutinesdemo.albums.model.local

import com.home.kotlincoroutinesdemo.common.model.local.AppDatabase

class DatabaseAlbumsHelperImpl(
    private val appDatabase: AppDatabase
) : DatabaseAlbumsHelper {

    override suspend fun insertAll(users: List<AlbumsEntity>) =
        appDatabase.albumsDao().insertAll(users)

    override suspend fun getAlbums(): List<AlbumsEntity> =
        appDatabase.albumsDao().getAll()
}