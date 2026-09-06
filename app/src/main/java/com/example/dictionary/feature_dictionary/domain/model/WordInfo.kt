package com.example.dictionary.feature_dictionary.domain.model

data class WordInfo(
    val word: String,
    val origin: String?,
    val phonetic: String?,
    val audioUrl: String?,
    val meanings: List<Meaning>
)
