package com.example.charactersdisney.characters.domain

import com.example.charactersdisney.characters.data.db.CharacterDbRepo
import com.example.charactersdisney.characters.ui.model.CharacterModel
import javax.inject.Inject

class AddCharacterOfflineDbUc @Inject constructor(private val characterDbRepo: CharacterDbRepo) {

    suspend operator fun invoke(characterModel: CharacterModel) {
        characterDbRepo.addCharacterOffline(characterModel)
    }

    suspend operator fun invoke():List<CharacterModel> = characterDbRepo.getCharactersOffline()
}