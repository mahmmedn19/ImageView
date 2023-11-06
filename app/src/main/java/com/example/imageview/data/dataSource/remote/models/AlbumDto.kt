package com.example.imageview.data.dataSource.remote.models

import com.google.gson.annotations.SerializedName

data class AlbumDto(
    @SerializedName("userId") val userId: Int,
    @SerializedName("id") val id: Int?,
    @SerializedName("title") val title: String?
)