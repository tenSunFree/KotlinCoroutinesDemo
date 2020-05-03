package com.home.kotlincoroutinesdemo.albums.model.remote

class ApiAlbumsHelperImpl(
    private val apiService: ApiAlbumsService
) : ApiAlbumsHelper {

    override suspend fun getAlbums() = apiService.getAlbums()
}