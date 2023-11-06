package com.example.imageview.domain.repository

import com.example.imageview.domain.model.UserAlbumPhoto
import com.example.imageview.domain.model.UserAlbums
import com.example.imageview.domain.model.UserInfo

interface UserRepository {

    suspend fun getUserInfo(userId: Int): UserInfo?

    suspend fun getUserAlbums(userId: Int): List<UserAlbums>?

    suspend fun getAlbumPhotos(albumId: Int): List<UserAlbumPhoto>?
}
