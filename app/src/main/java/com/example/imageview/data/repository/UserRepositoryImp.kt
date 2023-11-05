package com.example.imageview.data.repository

import com.example.imageview.data.dataSource.remote.mapper.toUserAlbumPhoto
import com.example.imageview.data.dataSource.remote.mapper.toUserAlbums
import com.example.imageview.data.dataSource.remote.mapper.toUserInfo
import com.example.imageview.data.dataSource.remote.network.ApiService
import com.example.imageview.domain.model.UserAlbumPhoto
import com.example.imageview.domain.model.UserAlbums
import com.example.imageview.domain.model.UserInfo
import com.example.imageview.domain.repository.UserRepository
import javax.inject.Inject


class UserRepositoryImp @Inject constructor(
    private val apiService: ApiService
): BaseRepository(), UserRepository {
    override suspend fun getUserInfo(userId: Int): UserInfo {
        return wrap { apiService.getUserInfo(userId) }.toUserInfo()
    }

    override suspend fun getUserAlbums(userId: Int): List<UserAlbums> {
        return wrap { apiService.getUserAlbums(userId) }.map { it.toUserAlbums() }
    }

    override suspend fun getAlbumPhotos(albumId: Int): List<UserAlbumPhoto> {
        return wrap { apiService.getAlbumPhotos(albumId) }.map { it.toUserAlbumPhoto() }
    }
}