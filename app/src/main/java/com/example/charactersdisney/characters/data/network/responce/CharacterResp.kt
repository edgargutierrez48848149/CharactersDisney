package com.example.charactersdisney.characters.data.network.responce

import com.example.charactersdisney.characters.ui.model.CharacterModel
import com.google.gson.annotations.SerializedName

data class CharacterResp(
    @SerializedName("info")
    val info:CharacterInfoResp,
    @SerializedName("data")
    val data:List<CharacterDataResp>
)
data class CharacterInfoResp(
    @SerializedName("count")
    val count: Int ?= null,
    @SerializedName("totalPages")
    val totalPages: Int ?= null,
    @SerializedName("previousPage")
    val previousPage: String ?= null,
    @SerializedName("nextPage")
    val nextPage: String ?= null,
)

data class CharacterDataResp(
    @SerializedName("_id")
    val id: Long ?= null,
    @SerializedName("films")
    val films: List<String> ?= null,
    @SerializedName("shortFilms")
    val shortFilms: List<String> ?= null,
    @SerializedName("tvShows")
    val tvShows: List<String> ?= null,
    @SerializedName("videoGames")
    val videoGames: List<String> ?= null,
    @SerializedName("parkAttractions")
    val parkAttractions: List<String> ?= null,
    @SerializedName("allies")
    val allies: List<String> ?= null,
    @SerializedName("enemies")
    val enemies: List<String> ?= null,
    @SerializedName("sourceUrl")
    val sourceUrl: String ?= null,
    @SerializedName("name")
    val name: String ?= null,
    @SerializedName("imageUrl")
    val imageUrl: String ?= null,
    @SerializedName("createdAt")
    val createdAt: String ?= null,
    @SerializedName("updatedAt")
    val updatedAt: String ?= null,
    @SerializedName("url")
    val url: String ?= null,
    @SerializedName("__v")
    val v: Int ?= null,
){
    fun toModel(): CharacterModel {
        return CharacterModel(
            id = id ?: 0,
            films = films ?: listOf(),
            shortFilms = shortFilms ?: listOf(),
            tvShows = tvShows ?: listOf(),
            videoGames = videoGames ?: listOf(),
            parkAttractions = parkAttractions ?: listOf(),
            allies = allies ?: listOf(),
            enemies = enemies ?: listOf(),
            name = name ?: "",
            imageUrl = imageUrl ?: "",
            createdAt = createdAt ?: "",
            updatedAt = updatedAt ?: "",
        )
    }
}
