package com.example.dictionary.core.util

import com.example.dictionary.core.error.DataError

sealed interface Resource<out T> {

    data object Loading : Resource<Nothing>

    data class Success<T>(
        val data: T
    ) : Resource<T>

    data class Error<T>(
        val error: DataError,
        val data: T? = null
    ) : Resource<T>
}
