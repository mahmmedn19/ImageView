package com.example.imageview.domain.usecase

import com.example.imageview.domain.model.UserAlbums
import com.example.imageview.domain.repository.UserRepository
import javax.inject.Inject
import kotlin.random.Random


class GetAlbumsUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(): List<UserAlbums>{
        val randomUserId = Random.nextInt(1, 11)
        return userRepository.getUserAlbums(randomUserId)?: emptyList()
    }
}