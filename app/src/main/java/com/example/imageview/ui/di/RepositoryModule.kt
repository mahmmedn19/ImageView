package com.example.imageview.ui.di

import com.example.imageview.data.dataSource.remote.network.ApiService
import com.example.imageview.data.repository.UserRepositoryImp
import com.example.imageview.domain.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun providePrayerRepo(
        prayerService: ApiService,
    ): UserRepository {
        return UserRepositoryImp(prayerService)
    }
}