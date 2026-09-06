package com.example.dictionary.feature_dictionary.data.local

import com.example.dictionary.feature_dictionary.data.local.entity.WordInfoEntity

interface WordInfoLocalDataSource {

    suspend fun getCachedWords(query: String): List<WordInfoEntity>

    suspend fun replaceWords(words: List<WordInfoEntity>)
}
