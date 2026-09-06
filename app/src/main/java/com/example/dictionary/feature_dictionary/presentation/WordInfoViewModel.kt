package com.example.dictionary.feature_dictionary.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dictionary.R
import com.example.dictionary.core.error.DataError
import com.example.dictionary.core.util.Resource
import com.example.dictionary.feature_dictionary.domain.use_case.SearchWordUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

@FlowPreview
@HiltViewModel
class WordInfoViewModel @Inject constructor(
    private val searchWordUseCase: SearchWordUseCase
) : ViewModel() {

    private val query = MutableStateFlow("")

    private val _state = MutableStateFlow(WordInfoState())
    val state = _state.asStateFlow()

    private val _events = MutableSharedFlow<WordInfoEvent>()
    val events = _events.asSharedFlow()

    init {
        observeSearchQuery()
    }

    fun onSearch(query: String) {
        _state.value = _state.value.copy(query = query)
        this.query.value = query
    }

    private fun observeSearchQuery() {
        viewModelScope.launch {
            query
                .debounce(500)
                .distinctUntilChanged()
                .collectLatest { searchQuery ->
                    searchWordUseCase(searchQuery).collect { result ->
                        when (result) {
                            Resource.Loading -> {
                                _state.value = _state.value.copy(
                                    isLoading = true
                                )
                            }

                            is Resource.Success -> {
                                _state.value = _state.value.copy(
                                    words = result.data,
                                    isLoading = false
                                )
                            }

                            is Resource.Error -> {
                                _state.value = _state.value.copy(
                                    words = result.data.orEmpty(),
                                    isLoading = false
                                )
                                _events.emit(
                                    WordInfoEvent.ShowError(
                                        result.error.toMessageResId()
                                    )
                                )
                            }
                        }
                    }
                }
        }
    }

    private fun DataError.toMessageResId(): Int {
        return when (this) {
            DataError.Network -> R.string.error_network
            DataError.NotFound -> R.string.error_not_found
            DataError.Server -> R.string.error_server
            DataError.Unknown -> R.string.error_unknown
        }
    }
}
