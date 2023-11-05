package com.example.imageview.data.dataSource.remote.mapper

import com.example.imageview.data.dataSource.remote.models.PhotoDto
import com.example.imageview.domain.model.UserAlbumPhoto

fun PhotoDto.toUserAlbumPhoto(): UserAlbumPhoto {
    return UserAlbumPhoto(
        albumId = albumId,
        id = id ?: 0,
        title = title ?: "",
        thumbnailUrl = thumbnailUrl ?: "",
    )
}