package com.gbreagan.catalog.data.datasource.remote

import com.gbreagan.catalog.data.model.Game
import retrofit2.http.GET

interface ApiService {
    @GET("games")
    suspend fun games() : List<Game>
}