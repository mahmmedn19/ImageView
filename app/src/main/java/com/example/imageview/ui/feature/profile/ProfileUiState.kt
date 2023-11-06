package com.example.imageview.ui.feature.profile

import com.example.imageview.domain.model.UserAlbums
import com.example.imageview.domain.model.UserInfo

data class ProfileUiState(
    val isLoading: Boolean = true,
    val isError: Boolean = false,
    val user: UserInfoUiState = UserInfoUiState(),
    val userAlbums: List<AlbumsUiState> = emptyList()
)

data class UserInfoUiState(
    val id: Int? = 0,
    val name: String? = "",
    val address: String = "",
)

data class AlbumsUiState(
    val id: Int? = 0,
    val title: String? = "",
    val userId: Int? = 0
)

fun UserInfo.toUserInfoUiState(): UserInfoUiState {
    val addressLine = listOfNotNull(
        address?.street,
        address?.suite,
        address?.city,
        address?.zipcode
    )
        .joinToString(", ")

    return UserInfoUiState(
        id = id,
        name = name,
        address = addressLine
    )
}

fun UserAlbums.toAlbumsUiState(): AlbumsUiState {
    return AlbumsUiState(
        id = id,
        title = title,
        userId = userId
    )
}