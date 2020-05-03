package com.home.kotlincoroutinesdemo.albums.model.local

import com.home.kotlincoroutinesdemo.common.model.local.DatabaseHelper

interface DatabaseAlbumsHelper : DatabaseHelper {

    suspend fun insertAll(users: List<AlbumsEntity>)

    suspend fun getAlbums(): List<AlbumsEntity>
}