package com.example.dictionary.core.error

sealed interface DataError {

    data object Network : DataError

    data object NotFound : DataError

    data object Server : DataError

    data object Unknown : DataError
}
