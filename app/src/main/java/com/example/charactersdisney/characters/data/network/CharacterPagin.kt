package com.example.charactersdisney.characters.data.network

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.charactersdisney.characters.ui.model.CharacterModel
import java.io.IOException
import javax.inject.Inject

class CharacterPagin @Inject constructor(private val characterApi: CharacterApi): PagingSource<Int, CharacterModel>(){

    override fun getRefreshKey(state: PagingState<Int, CharacterModel>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CharacterModel> {
        return try {
            var page = params.key ?: 1
            if (page == 0 ) page = 1
            val response = characterApi.getCharacter(page,25)

            val prevKey = if (response.info.previousPage != null) page - 1 else null
            val nextKey = if (response.info.nextPage != null) page + 1 else null

            LoadResult.Page(
                data = response.data.map { it.toModel() },
                prevKey = prevKey,
                nextKey = nextKey
            )
        } catch (exception: IOException) {
            LoadResult.Error(exception)
        }
    }
}