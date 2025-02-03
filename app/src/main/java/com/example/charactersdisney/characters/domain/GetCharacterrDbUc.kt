package com.example.charactersdisney.characters.domain

import com.example.charactersdisney.characters.data.db.CharacterDbRepo
import com.example.charactersdisney.characters.ui.model.CharacterModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCharacterrDbUc @Inject constructor(private val characterDbRepo: CharacterDbRepo){

    operator fun invoke(): Flow<List<CharacterModel>> = characterDbRepo.getCharacters

}