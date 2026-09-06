package com.example.dictionary.feature_dictionary.data.repository

import com.example.dictionary.core.error.DataError
import com.example.dictionary.core.util.Resource
import com.example.dictionary.feature_dictionary.data.local.WordInfoLocalDataSource
import com.example.dictionary.feature_dictionary.data.mapper.toDomain
import com.example.dictionary.feature_dictionary.data.mapper.toEntity
import com.example.dictionary.feature_dictionary.data.remote.WordInfoRemoteDataSource
import com.example.dictionary.feature_dictionary.data.util.JsonParser
import com.example.dictionary.feature_dictionary.domain.model.WordInfo
import com.example.dictionary.feature_dictionary.domain.repository.WordInfoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class WordInfoRepositoryImpl @Inject constructor(
    private val remoteDataSource: WordInfoRemoteDataSource,
    private val localDataSource: WordInfoLocalDataSource,
    private val jsonParser: JsonParser
) : WordInfoRepository {

    override fun searchWord(word: String): Flow<Resource<List<WordInfo>>> = flow {
        val cachedWords = localDataSource
            .getCachedWords(word)
            .map { it.toDomain(jsonParser) }

        emit(Resource.Loading)
        if (cachedWords.isNotEmpty()) {
            emit(Resource.Success(cachedWords))
            emit(Resource.Loading)
        }

        try {
            val remoteWords = remoteDataSource
                .getWordInfo(word)
                .map { it.toDomain() }

            localDataSource.replaceWords(
                remoteWords.map { it.toEntity(jsonParser) }
            )

            val freshWords = localDataSource
                .getCachedWords(word)
                .map { it.toDomain(jsonParser) }

            emit(Resource.Success(freshWords))
        } catch (exception: HttpException) {
            val error = when (exception.code()) {
                404 -> DataError.NotFound
                else -> DataError.Server
            }

            emit(Resource.Error(error, cachedWords))
        } catch (_: IOException) {
            emit(Resource.Error(DataError.Network, cachedWords))
        } catch (_: Exception) {
            emit(Resource.Error(DataError.Unknown, cachedWords))
        }
    }
}
