package com.example.charactersdisney.characters.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.charactersdisney.characters.data.db.entity.CharacterEntity
import com.example.charactersdisney.characters.data.db.entity.CharacterOfflineEntity

@Database(entities = [CharacterEntity::class, CharacterOfflineEntity::class], version = 2)
@TypeConverters(CharacterTypeConverters::class)
abstract class CharacterDb:RoomDatabase() {
    abstract fun characterDao(): CharacterDao
}