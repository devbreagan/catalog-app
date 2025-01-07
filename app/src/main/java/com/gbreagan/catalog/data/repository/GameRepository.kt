package com.gbreagan.catalog.data.repository

import androidx.paging.PagingData
import com.gbreagan.catalog.data.model.Game
import kotlinx.coroutines.flow.Flow

interface GameRepository {
    fun getPagedGameItems(): Flow<PagingData<Game>>
    suspend fun getGameItem(id: Int): Flow<Game>
}