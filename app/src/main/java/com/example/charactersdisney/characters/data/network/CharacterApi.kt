package com.example.charactersdisney.characters.data.network


import com.example.charactersdisney.characters.data.network.responce.CharacterResp
import retrofit2.http.GET
import retrofit2.http.Query

interface CharacterApi {

    @GET("character")
    suspend fun getCharacter(
        @Query("page") page:Int,
        @Query("pageSize") pageSize:Int
    ): CharacterResp

    @GET("character")
    suspend fun getCharacterSearch(
        @Query("name") name:String
    ): CharacterResp
}