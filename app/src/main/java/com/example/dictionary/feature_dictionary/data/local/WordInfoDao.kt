package com.example.dictionary.feature_dictionary.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.dictionary.feature_dictionary.data.local.entity.WordInfoEntity

@Dao
interface WordInfoDao {

    @Query("SELECT * FROM word_info WHERE word LIKE :query || '%' ORDER BY word")
    suspend fun getWordsStartingWith(query: String): List<WordInfoEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(words: List<WordInfoEntity>)

    @Query("DELETE FROM word_info WHERE word IN (:words)")
    suspend fun deleteByWords(words: List<String>)
}
