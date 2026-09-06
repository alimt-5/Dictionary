package com.example.dictionary.feature_dictionary.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "word_info")
data class WordInfoEntity(
    @PrimaryKey
    val word: String,
    val origin: String?,
    val phonetic: String?,
    val audioUrl: String?,
    val meaningsJson: String
)
