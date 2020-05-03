package com.home.kotlincoroutinesdemo.albums.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.home.kotlincoroutinesdemo.albums.model.local.AlbumsEntity
import com.home.kotlincoroutinesdemo.albums.model.local.DatabaseAlbumsHelper
import com.home.kotlincoroutinesdemo.albums.model.remote.ApiAlbumsHelper
import com.home.kotlincoroutinesdemo.common.model.Resource
import kotlinx.coroutines.launch

class AlbumsViewModel(
    private val apiHelper: ApiAlbumsHelper,
    private val dbHelper: DatabaseAlbumsHelper
) : ViewModel() {

    private val albums = MutableLiveData<Resource<List<AlbumsEntity>>>()

    init {
        fetchUsers()
    }

    private fun fetchUsers() {
        viewModelScope.launch {
            albums.postValue(Resource.loading(null))
            try {
                var albumsDb = dbHelper.getAlbums()
                if (albumsDb.isEmpty()) {
                    val albumsApi = apiHelper.getAlbums()
                    albumsDb = mutableListOf()
                    for (response in albumsApi) {
                        val user = AlbumsEntity(
                            response.userId,
                            response.id,
                            response.title
                        )
                        albumsDb.add(user)
                    }
                    dbHelper.insertAll(albumsDb)
                    albums.postValue(Resource.success(albumsDb))
                } else {
                    albums.postValue(Resource.success(albumsDb))
                }
            } catch (e: Exception) {
                albums.postValue(Resource.error(e.toString(), null))
            }
        }
    }

    fun getAlbums(): LiveData<Resource<List<AlbumsEntity>>> {
        return albums
    }
}