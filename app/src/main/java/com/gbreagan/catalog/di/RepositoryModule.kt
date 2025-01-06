package com.gbreagan.catalog.di

import com.gbreagan.catalog.data.datasource.local.CatalogDatabase
import com.gbreagan.catalog.data.datasource.remote.ApiService
import com.gbreagan.catalog.data.repository.GameRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier
import javax.inject.Singleton

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class Local

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class Remote

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideGameRepository(
        apiService: ApiService,
        gameDao: CatalogDatabase
    ): GameRepository {
        return GameRepository(apiService, gameDao)
    }
}