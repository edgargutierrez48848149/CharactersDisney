package com.example.charactersdisney.characters.data.network

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.charactersdisney.characters.ui.model.CharacterModel
import com.example.charactersdisney.common.MAX_ITEMS
import com.example.charactersdisney.common.PREF_ITEMS
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CharacterNetRepo @Inject constructor(val characterApi: CharacterApi){

    fun getCharacter():Flow<PagingData<CharacterModel>>{
        return Pager(config = PagingConfig(pageSize = MAX_ITEMS, prefetchDistance = PREF_ITEMS),
            pagingSourceFactory = {
                CharacterPagin(characterApi)
            }).flow
    }

    suspend fun getCharacterSearch(name:String): List<CharacterModel> {
        return try {
            characterApi.getCharacterSearch(name).data.map { it.toModel() }
        }catch (_:Exception){
            listOf()
        }
    }
}