package com.home.kotlincoroutinesdemo.albums.model.remote

import retrofit2.http.GET

interface ApiAlbumsService {

    @GET("albums")
    suspend fun getAlbums(): List<AlbumsResponse>
}