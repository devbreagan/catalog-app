package com.gbreagan.catalog.di

import com.gbreagan.catalog.data.repository.GameRepository
import com.gbreagan.catalog.data.repository.remote.GameRemoteRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun bindGameRepository(gameRepository: GameRemoteRepository) : GameRepository
}