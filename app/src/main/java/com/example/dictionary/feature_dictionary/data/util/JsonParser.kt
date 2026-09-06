package com.example.dictionary.feature_dictionary.data.util

import java.lang.reflect.Type

interface JsonParser {

    fun <T> fromJson(json: String, type: Type): T?

    fun <T> toJson(value: T, type: Type): String
}
