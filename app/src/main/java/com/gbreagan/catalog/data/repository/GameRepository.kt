package com.gbreagan.catalog.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.gbreagan.catalog.data.datasource.local.CatalogDatabase
import com.gbreagan.catalog.data.datasource.remote.ApiService
import com.gbreagan.catalog.data.model.Game
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject

class GameRepository @Inject constructor(
    private val apiService: ApiService,
    private val database: CatalogDatabase
) {
    @OptIn(ExperimentalPagingApi::class)
    fun getPagedGameItems(): Flow<PagingData<Game>> = Pager(
        config = PagingConfig(pageSize = 10, enablePlaceholders = false),
        pagingSourceFactory = { database.getGameDao().selectAll() },
        remoteMediator = ItemRemoteMediator(apiService, database),
    ).flow

    suspend fun getGameItem(id: Int): Flow<Game> = flow { emit(database.getGameDao().selectById(id)) }
}

@OptIn(ExperimentalPagingApi::class)
class ItemRemoteMediator(
    private val apiService: ApiService,
    private val database: CatalogDatabase
) : RemoteMediator<Int, Game>() {

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, Game>
    ): MediatorResult {
        return try {
            var response = listOf<Game>()
            CoroutineScope(Dispatchers.IO).launch {
                if (database.getGameDao().count() == 0) {
                    response = apiService.games()
                    database.withTransaction {
                        if (loadType == LoadType.REFRESH) {
                            database.getGameDao().deleteAll()
                        }
                        database.getGameDao().insertAll(response)
                    }
                }
            }
            MediatorResult.Success(endOfPaginationReached = response.isEmpty())
        } catch (e: Exception) {
            MediatorResult.Error(e)
        }
    }
}