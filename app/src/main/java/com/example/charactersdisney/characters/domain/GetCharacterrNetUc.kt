package com.example.charactersdisney.characters.domain

import androidx.paging.PagingData
import com.example.charactersdisney.characters.data.db.CharacterDbRepo
import com.example.charactersdisney.characters.data.network.CharacterNetRepo
import com.example.charactersdisney.characters.ui.model.CharacterModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCharacterrNetUc @Inject constructor(private val characterNetRepo: CharacterNetRepo){
    operator fun invoke(): Flow<PagingData<CharacterModel>> = characterNetRepo.getCharacter()
}