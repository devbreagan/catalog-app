package com.gbreagan.catalog.data.datasource.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.gbreagan.catalog.data.model.Game

@Dao
interface GameDao {
    @Query("SELECT COUNT(*) FROM game")
    fun count(): Int

    @Query("SELECT * FROM game ORDER BY id ASC")
    fun selectAll(): PagingSource<Int, Game>

    @Query("SELECT * FROM game WHERE id = :id")
    suspend fun selectById(id: Int): Game

    @Query("SELECT * FROM game WHERE title LIKE '%' || :title || '%'")
    suspend fun selectByTitleLikely(title: String): List<Game>

    @Query("SELECT * FROM game WHERE genre = :genre")
    suspend fun selectByGenre(genre: String): List<Game>

    @Query("SELECT DISTINCT genre FROM game")
    suspend fun selectAllGenres(): List<String>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOne(game: Game)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(games: List<Game>)

    @Query("DELETE FROM game")
    suspend fun deleteAll()
}
