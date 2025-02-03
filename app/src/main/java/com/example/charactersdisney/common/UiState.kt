package com.example.charactersdisney.common

import com.example.charactersdisney.characters.ui.model.CharacterModel

interface UiState {
    object Loading:UiState
    data class Error(val throwable: Throwable):UiState
    data class Succes(val listCharacter:List<CharacterModel>):UiState
}