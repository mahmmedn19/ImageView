package com.example.imageview.domain.usecase

import com.example.imageview.domain.model.UserAlbums
import com.example.imageview.domain.model.UserInfo
import com.example.imageview.domain.repository.UserRepository
import javax.inject.Inject
import kotlin.random.Random


class GetProfileInfoUseCase @Inject constructor(
    private val userRepository: UserRepository,
) {
    suspend  fun getUserInfo(): UserInfo? {
        val randomUserId = Random.nextInt(1,11 )
        return userRepository.getUserInfo(randomUserId)
    }
    suspend  fun getAlbums(): List<UserAlbums>{
        return getUserInfo()?.id?.let { userRepository.getUserAlbums(it) } ?: emptyList()
    }



}