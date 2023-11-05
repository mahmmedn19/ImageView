package com.example.imageview.data.dataSource.remote.mapper

import com.example.imageview.data.dataSource.remote.models.AlbumDto
import com.example.imageview.domain.model.UserAlbums

fun AlbumDto.toUserAlbums(): UserAlbums {
    return UserAlbums(
        userId = userId,
        id = id ?: 0,
        title = title ?: ""
    )
}