package com.example.dictionary.feature_dictionary.data.remote

import com.example.dictionary.feature_dictionary.data.remote.dto.WordInfoDto
import javax.inject.Inject

class WordInfoRemoteDataSourceImpl @Inject constructor(
    private val api: DictionaryApi
) : WordInfoRemoteDataSource {

    override suspend fun getWordInfo(word: String): List<WordInfoDto> {
        return api.getWordInfo(word)
    }
}
