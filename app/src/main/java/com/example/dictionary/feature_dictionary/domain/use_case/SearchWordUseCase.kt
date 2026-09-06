package com.example.dictionary.feature_dictionary.domain.use_case

import com.example.dictionary.core.util.Resource
import com.example.dictionary.feature_dictionary.domain.model.WordInfo
import com.example.dictionary.feature_dictionary.domain.repository.WordInfoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class SearchWordUseCase @Inject constructor(
    private val repository: WordInfoRepository
) {

    operator fun invoke(query: String): Flow<Resource<List<WordInfo>>> {
        val normalizedQuery = query.trim().lowercase()

        if (normalizedQuery.isBlank()) {
            return flowOf(Resource.Success(emptyList()))
        }

        return repository.searchWord(normalizedQuery)
    }
}
