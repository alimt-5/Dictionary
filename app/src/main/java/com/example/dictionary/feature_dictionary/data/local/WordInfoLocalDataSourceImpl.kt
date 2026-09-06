package com.example.dictionary.feature_dictionary.data.local

import com.example.dictionary.feature_dictionary.data.local.entity.WordInfoEntity
import javax.inject.Inject

class WordInfoLocalDataSourceImpl @Inject constructor(
    private val dao: WordInfoDao
) : WordInfoLocalDataSource {

    override suspend fun getCachedWords(query: String): List<WordInfoEntity> {
        return dao.getWordsStartingWith(query)
    }

    override suspend fun replaceWords(words: List<WordInfoEntity>) {
        if (words.isEmpty()) return

        dao.deleteByWords(words.map(WordInfoEntity::word))
        dao.upsert(words)
    }
}
