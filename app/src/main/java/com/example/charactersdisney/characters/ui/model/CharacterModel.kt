package com.example.charactersdisney.characters.ui.model

import com.example.charactersdisney.characters.data.db.entity.CharacterEntity
import com.example.charactersdisney.characters.data.db.entity.CharacterOfflineEntity

data class CharacterModel(
    val id:Long = 0,
    val films:List<String> = listOf(),
    val shortFilms:List<String> = listOf(),
    val tvShows:List<String> = listOf(),
    val videoGames:List<String> = listOf(),
    val parkAttractions:List<String> = listOf(),
    val allies:List<String> = listOf(),
    val enemies:List<String> = listOf(),
    val name:String = "",
    val imageUrl:String = "",
    val createdAt:String = "",
    val updatedAt:String = ""
) {
    fun toEntity(): CharacterEntity {
        return CharacterEntity(
            id = id,
            films = films,
            shortFilms = shortFilms,
            tvShows = tvShows,
            videoGames = videoGames,
            parkAttractions = parkAttractions,
            allies = allies,
            enemies = enemies,
            name = name,
            imageUrl = imageUrl,
            createdAt = createdAt,
            updatedAt = updatedAt,
        )
    }

    fun toOfflineEntity(): CharacterOfflineEntity {
        return CharacterOfflineEntity(
            id = id,
            films = films,
            shortFilms = shortFilms,
            tvShows = tvShows,
            videoGames = videoGames,
            parkAttractions = parkAttractions,
            allies = allies,
            enemies = enemies,
            name = name,
            imageUrl = imageUrl,
            createdAt = createdAt,
            updatedAt = updatedAt,
        )
    }
}
