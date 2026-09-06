package com.example.dictionary.feature_dictionary.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.dictionary.feature_dictionary.data.local.entity.WordInfoEntity

@Database(
    entities = [WordInfoEntity::class],
    version = 1,
    exportSchema = false
)
abstract class WordInfoDatabase : RoomDatabase() {

    abstract fun wordInfoDao(): WordInfoDao
}
