package com.example.dictionary.feature_dictionary.data.mapper

import com.example.dictionary.feature_dictionary.data.local.entity.WordInfoEntity
import com.example.dictionary.feature_dictionary.data.remote.dto.WordInfoDto
import com.example.dictionary.feature_dictionary.data.util.JsonParser
import com.example.dictionary.feature_dictionary.domain.model.Definition
import com.example.dictionary.feature_dictionary.domain.model.Meaning
import com.example.dictionary.feature_dictionary.domain.model.WordInfo
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

private val meaningsType: Type = object : TypeToken<List<Meaning>>() {}.type

fun WordInfoDto.toDomain(): WordInfo {
    return WordInfo(
        word = word,
        origin = origin,
        phonetic = phonetic ?: phonetics.firstOrNull { !it.audio.isNullOrBlank() }?.text,
        audioUrl = phonetics.firstOrNull { !it.audio.isNullOrBlank() }?.audio,
        meanings = meanings.map { meaning ->
            Meaning(
                partOfSpeech = meaning.partOfSpeech,
                definitions = meaning.definitions.map { definition ->
                    Definition(
                        definition = definition.definition,
                        example = definition.example,
                        synonyms = definition.synonyms,
                        antonyms = definition.antonyms
                    )
                }
            )
        }
    )
}

fun WordInfoEntity.toDomain(parser: JsonParser): WordInfo {
    return WordInfo(
        word = word,
        origin = origin,
        phonetic = phonetic,
        audioUrl = audioUrl,
        meanings = parser.fromJson<List<Meaning>>(meaningsJson, meaningsType).orEmpty()
    )
}

fun WordInfo.toEntity(parser: JsonParser): WordInfoEntity {
    return WordInfoEntity(
        word = word,
        origin = origin,
        phonetic = phonetic,
        audioUrl = audioUrl,
        meaningsJson = parser.toJson(meanings, meaningsType)
    )
}
