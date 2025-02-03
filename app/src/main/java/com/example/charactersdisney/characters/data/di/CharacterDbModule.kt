package com.example.charactersdisney.characters.data.di

import android.content.Context
import androidx.room.Room
import com.example.charactersdisney.characters.data.db.CharacterDao
import com.example.charactersdisney.characters.data.db.CharacterDb
import com.example.charactersdisney.common.NAME_DATA_BASE
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class CharacterDbModule {

    @Provides
    fun provideCharacterDao(characterDb: CharacterDb): CharacterDao {
        return characterDb.characterDao()
    }

    @Provides
    @Singleton
    fun provideCharacterDb(@ApplicationContext context: Context): CharacterDb {
        return Room.databaseBuilder(context, CharacterDb::class.java,NAME_DATA_BASE).build()
    }
}