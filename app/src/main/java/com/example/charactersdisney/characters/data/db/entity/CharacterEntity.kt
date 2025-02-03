package com.example.charactersdisney.characters.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.charactersdisney.characters.ui.model.CharacterModel

@Entity(tableName = "tbl_character")
data class CharacterEntity(
    @PrimaryKey
    val id: Long? = 0,
    val films: List<String>? = listOf(),
    val shortFilms: List<String>? = listOf(),
    val tvShows: List<String>? = listOf(),
    val videoGames: List<String>? = listOf(),
    val parkAttractions: List<String>? = listOf(),
    val allies: List<String>? = listOf(),
    val enemies: List<String>? = listOf(),
    val name: String? = "",
    val imageUrl: String? = "",
    val createdAt: String? = "",
    val updatedAt: String? = ""
) {
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
