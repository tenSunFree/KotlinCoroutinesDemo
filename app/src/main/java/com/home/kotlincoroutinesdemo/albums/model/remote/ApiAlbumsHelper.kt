package com.home.kotlincoroutinesdemo.albums.model.remote

import com.home.kotlincoroutinesdemo.common.model.remote.ApiHelper

interface ApiAlbumsHelper : ApiHelper {

    suspend fun getAlbums(): List<AlbumsResponse>
}