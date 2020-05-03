package com.home.kotlincoroutinesdemo.common

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.home.kotlincoroutinesdemo.albums.model.local.DatabaseAlbumsHelper
import com.home.kotlincoroutinesdemo.albums.model.remote.ApiAlbumsHelper
import com.home.kotlincoroutinesdemo.albums.viewmodel.AlbumsViewModel
import com.home.kotlincoroutinesdemo.common.model.local.DatabaseHelper
import com.home.kotlincoroutinesdemo.common.model.remote.ApiHelper

class ViewModelFactory(
    private val apiHelper: ApiHelper,
    private val dbHelper: DatabaseHelper
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AlbumsViewModel::class.java)) {
            val apiHelper = apiHelper as ApiAlbumsHelper
            val dbHelper = dbHelper as DatabaseAlbumsHelper
            return AlbumsViewModel(apiHelper, dbHelper) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}