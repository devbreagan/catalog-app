package com.gbreagan.catalog.data.repository

import com.gbreagan.catalog.data.model.Game
import kotlinx.coroutines.flow.Flow

interface GameRepository {
    suspend fun games() : Flow<List<Game>>
}