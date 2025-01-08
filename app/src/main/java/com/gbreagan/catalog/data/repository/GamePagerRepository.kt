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

class GamePagerRepository @Inject constructor(
    private val apiService: ApiService,
    private val database: CatalogDatabase
): GameRepository {
    @OptIn(ExperimentalPagingApi::class)
    override fun getPagedGameItems(): Flow<PagingData<Game>> = Pager(
        config = PagingConfig(pageSize = 10, enablePlaceholders = false),
        pagingSourceFactory = { database.getGameDao().selectAll() },
        remoteMediator = ItemRemoteMediator(apiService, database),
    ).flow

    override suspend fun getGameItem(id: Int): Flow<Game> = flow { emit(database.getGameDao().selectById(id)) }
    override suspend fun findItemsByTitle(title: String): Flow<List<Game>> = flow { emit(database.getGameDao().selectByTitleLikely(title)) }
    override suspend fun findItemsByGenre(genre: String): Flow<List<Game>> = flow { emit(database.getGameDao().selectByGenre(genre)) }
    override suspend fun findAllGenres(): Flow<List<String>> = flow { emit(database.getGameDao().selectAllGenres()) }
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