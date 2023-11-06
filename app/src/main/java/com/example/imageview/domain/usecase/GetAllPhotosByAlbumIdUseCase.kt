package com.example.imageview.domain.usecase

import com.example.imageview.domain.model.UserAlbumPhoto
import com.example.imageview.domain.repository.UserRepository
import javax.inject.Inject


class GetAllPhotosByAlbumIdUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(albumId: Int): List<UserAlbumPhoto> =
        userRepository.getAlbumPhotos(albumId)?: emptyList()
}