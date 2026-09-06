package com.example.dictionary.feature_dictionary.data.remote.dto

import com.google.gson.annotations.SerializedName

data class WordInfoDto(
    val word: String = "",
    val origin: String? = null,
    val phonetic: String? = null,
    val phonetics: List<PhoneticDto> = emptyList(),
    val meanings: List<MeaningDto> = emptyList()
)

data class PhoneticDto(
    val text: String? = null,
    val audio: String? = null
)

data class MeaningDto(
    @SerializedName("partOfSpeech")
    val partOfSpeech: String = "",
    val definitions: List<DefinitionDto> = emptyList()
)

data class DefinitionDto(
    val definition: String = "",
    val example: String? = null,
    val synonyms: List<String> = emptyList(),
    val antonyms: List<String> = emptyList()
)
