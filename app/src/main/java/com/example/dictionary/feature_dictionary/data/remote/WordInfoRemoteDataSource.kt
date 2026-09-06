package com.example.dictionary.feature_dictionary.data.remote

import com.example.dictionary.feature_dictionary.data.remote.dto.WordInfoDto

interface WordInfoRemoteDataSource {

    suspend fun getWordInfo(word: String): List<WordInfoDto>
}
