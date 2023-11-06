package com.example.imageview.data.dataSource.remote.network

import com.example.imageview.data.dataSource.remote.models.AlbumDto
import com.example.imageview.data.dataSource.remote.models.PhotoDto
import com.example.imageview.data.dataSource.remote.models.UserDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("users/{userId}")
    suspend fun getUserInfo(@Path("userId") userId: Int): Response<UserDto>

    @GET("/albums")
    suspend fun getUserAlbums(@Query("userId") userId: Int): Response<List<AlbumDto>>

    @GET("/photos")
    suspend fun getAlbumPhotos(@Query("albumId") albumId: Int): Response<List<PhotoDto>>
}