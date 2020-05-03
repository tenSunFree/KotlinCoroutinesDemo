package com.home.kotlincoroutinesdemo.albums.model.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class AlbumsEntity(
    @ColumnInfo(name = "userId") val userId: Int?,
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "title") val title: String?
)