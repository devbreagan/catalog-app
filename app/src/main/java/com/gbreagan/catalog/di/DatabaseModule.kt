package com.gbreagan.catalog.di

import android.content.Context
import androidx.room.Room
import com.gbreagan.catalog.data.datasource.local.CatalogDatabase
import com.gbreagan.catalog.data.datasource.local.GameDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideCatalogDatabase(@ApplicationContext context: Context): CatalogDatabase {
        return Room.databaseBuilder(
            context,
            CatalogDatabase::class.java,
            "catalog.db"
        ).build()
    }

    @Singleton
    @Provides
    fun provideGameDao(catalogDatabase: CatalogDatabase): GameDao = catalogDatabase.getGameDao()

}