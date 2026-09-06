package com.example.dictionary.feature_dictionary.presentation

sealed interface WordInfoEvent {
    data class ShowError(val messageResId: Int) : WordInfoEvent
}
