package com.gbreagan.catalog.data.datasource.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.gbreagan.catalog.data.model.Game

@Database(version = 1, entities = [Game::class], exportSchema = false)
abstract class CatalogDatabase : RoomDatabase() {
    abstract fun getGameDao(): GameDao
}