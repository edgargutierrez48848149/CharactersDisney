package com.example.charactersdisney.characters.domain

import com.example.charactersdisney.characters.data.db.CharacterDbRepo
import com.example.charactersdisney.characters.ui.model.CharacterModel
import javax.inject.Inject

class GetCharacterSearchDbUc @Inject constructor(private val characterDbRepo: CharacterDbRepo) {

    suspend operator fun invoke(id:Long): CharacterModel? =
        characterDbRepo.getCharacterSearch(id)

}