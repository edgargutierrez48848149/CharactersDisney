package com.example.charactersdisney.characters.data.db

import androidx.room.TypeConverter

class CharacterTypeConverters {
    @TypeConverter
    fun listToString(list: List<String>): String = list.toString()

    @TypeConverter
    fun stringToList(stringList: String): List<String> {
        val result = ArrayList<String>()
        val split =stringList.replace("[","").replace("]","").replace(" ","").split(",")
        for (n in split) {
            try {
                result.add(n)
            } catch (_: Exception) {

            }
        }
        return result
    }
}