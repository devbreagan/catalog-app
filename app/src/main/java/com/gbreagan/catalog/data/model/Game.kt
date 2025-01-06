package com.gbreagan.catalog.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "game")
data class Game(
    @SerializedName("developer") val developer: String,
    @SerializedName("freetogame_profile_url") val gameProfileUrl: String,
    @SerializedName("game_url") val gameUrl: String,
    @SerializedName("genre") val genre: String,
    @PrimaryKey(autoGenerate = false)
    @SerializedName("id") val id: Int,
    @SerializedName("platform") val platform: String,
    @SerializedName("publisher") val publisher: String,
    @SerializedName("release_date") val releaseDate: String,
    @SerializedName("short_description") val shortDescription: String,
    @SerializedName("thumbnail") val thumbnail: String,
    @SerializedName("title") val title: String
)