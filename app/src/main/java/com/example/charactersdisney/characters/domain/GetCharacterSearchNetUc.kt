package com.example.charactersdisney.characters.domain

import com.example.charactersdisney.characters.data.network.CharacterNetRepo
import com.example.charactersdisney.characters.ui.model.CharacterModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCharacterSearchNetUc @Inject constructor(private val characterNetRepo: CharacterNetRepo){
    suspend operator fun invoke(name:String): List<CharacterModel> = characterNetRepo.getCharacterSearch(name)
}