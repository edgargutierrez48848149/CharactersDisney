package com.example.charactersdisney.characters.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.charactersdisney.characters.data.db.entity.CharacterEntity
import com.example.charactersdisney.characters.data.db.entity.CharacterOfflineEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CharacterDao {

    @Query("SELECT * FROM tbl_character")
    fun getCharacters(): Flow<List<CharacterEntity>>

    @Query("SELECT * FROM tbl_character WHERE id =:id")
    suspend fun getCharacterSearch(id:Long): CharacterEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addCharacter(item: CharacterEntity)

    @Query("SELECT * FROM tbl_character_offline")
    suspend fun getCharactersOffline(): List<CharacterOfflineEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addCharacterOffline(item: CharacterOfflineEntity)

    @Delete
    suspend fun deleteCharacter(characterEntity: CharacterEntity)
}