package com.gbreagan.catalog.data.datasource.remote

import com.gbreagan.catalog.data.model.Game
import com.gbreagan.catalog.data.repository.GameRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

//class RemoteDataSource @Inject constructor(
//    private val apiService: ApiService
//) : GameRepository {
//    override suspend fun games(): Flow<List<Game>> {
//        return flow { emit(apiService.games()) }
//    }
//}