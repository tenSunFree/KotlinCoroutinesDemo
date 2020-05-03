package com.home.kotlincoroutinesdemo.common.model.remote

import com.home.kotlincoroutinesdemo.albums.model.remote.ApiAlbumsService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBuilder {

    private const val BASE_URL = "https://jsonplaceholder.typicode.com/"

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val apiAlbumsService: ApiAlbumsService = getRetrofit()
        .create(ApiAlbumsService::class.java)
}