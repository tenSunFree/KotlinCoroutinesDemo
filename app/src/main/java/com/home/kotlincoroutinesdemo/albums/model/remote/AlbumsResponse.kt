package com.home.kotlincoroutinesdemo.albums.model.remote

data class AlbumsResponse(
    val userId: Int = 0,
    val id: Int = 0,
    val title: String = ""
)