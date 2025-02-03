package com.example.charactersdisney.characters.data.db

import com.example.charactersdisney.characters.data.db.entity.CharacterOfflineEntity
import com.example.charactersdisney.characters.ui.model.CharacterModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CharacterDbRepo @Inject constructor(private val characterDao: CharacterDao) {

    val getCharacters:Flow<List<CharacterModel>> =
        characterDao.getCharacters().map { items-> items.map { it.toModel() } }

    suspend fun getCharacterSearch(id:Long):CharacterModel?{
        return characterDao.getCharacterSearch(id)?.toModel()
    }

    suspend fun addCharacter(characterModel: CharacterModel){
        characterDao.addCharacter(characterModel.toEntity())
    }

    suspend fun getCharactersOffline():List<CharacterModel> =
        characterDao.getCharactersOffline().map { it.toModel() }

    suspend fun addCharacterOffline(characterModel: CharacterModel){
        characterDao.addCharacterOffline(characterModel.toOfflineEntity())
    }

    suspend fun deleteCharacter(characterModel: CharacterModel){
        characterDao.deleteCharacter(characterModel.toEntity())
    }


}