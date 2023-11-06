package com.example.imageview.ui.feature.album

import com.example.imageview.domain.model.UserAlbumPhoto

data class AlbumUiState(
    val isLoading: Boolean = true,
    val isError: Boolean = false,
    val albumPhotos: List<AlbumPhotos> = emptyList(),
    val searchedPhoto : List<AlbumPhotos> = emptyList()
)

data class AlbumPhotos(
    val albumId: Int?,
    val id: Int?,
    val thumbnailUrl: String?,
    val title: String?,
)

fun UserAlbumPhoto.toAlbumPhotos(): AlbumPhotos {
    return AlbumPhotos(
        albumId = albumId,
        id = id,
        thumbnailUrl = thumbnailUrl,
        title = title
    )
}
