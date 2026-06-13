package com.example.dictionary.feature_dictionary.data.repository

import com.example.dictionary.core.util.Resource
import com.example.dictionary.feature_dictionary.data.local.WordInfoDao
import com.example.dictionary.feature_dictionary.data.remote.DictionaryApi
import com.example.dictionary.feature_dictionary.domain.model.WordInfo
import com.example.dictionary.feature_dictionary.domain.repository.WordInfoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class WordInfoRepositoryImpl(
    private val api: DictionaryApi,
    private val dao: WordInfoDao
) : WordInfoRepository {

    override fun getWordInfo(word: String): Flow<Resource<List<WordInfo>>> = flow {
        emit(Resource.Loading())

        val wordInfos = dao.getWordInfos(word).map { it.toWordInfo() }
        emit(Resource.Loading(data = wordInfos))

        try {
            val remoteWordInfos = api.getWordInfo(word)
            dao.deleteWordInfos(remoteWordInfos.map { it.word })
            dao.insertWordInfos(remoteWordInfos.map { it.toWordInfoEntity() })
        } catch (e: HttpException) {
            emit(
                Resource.Error(
                    message = "Oops, something went wrong!",
                    data = wordInfos
                )
            )
        } catch (e: IOException) {
            emit(
                Resource.Error(
                    message = "Couldn't reach server, check your internet connection.",
                    data = wordInfos
                )
            )
        }
        val newWordInfos = dao.getWordInfos(word).map { it.toWordInfo() }
        emit(Resource.Success(newWordInfos))
    }
}

//package com.example.dictionary.feature_dictionary.data.repository
//
//
//import com.example.dictionary.core.util.Resource
//import com.example.dictionary.feature_dictionary.data.local.WordInfoDao
//import com.example.dictionary.feature_dictionary.data.remote.DictionaryApi
//import com.example.dictionary.feature_dictionary.domain.model.WordInfo
//import com.example.dictionary.feature_dictionary.domain.repository.WordInfoRepository
//import kotlinx.coroutines.flow.Flow
//import kotlinx.coroutines.flow.flow
//import retrofit2.HttpException
//import java.io.IOException
//
//class WordInfoRepositoryImpl(
//    private val api: DictionaryApi,
//    private val dao: WordInfoDao
//) : WordInfoRepository {
//
//    override fun getWordInfo(word: String): Flow<Resource<List<WordInfo>>> = flow {
//        // 1. حالت لودینگ اولیه
//        emit(Resource.Loading())
//
//        // 2. دریافت داده‌های کش شده از دیتابیس (در صورت وجود خطا در خواندن، لیست خالی برگردان)
//        val cachedWords = runCatching {
//            dao.getWordInfos(word).map { it.toWordInfo() }
//        }.getOrElse { emptyList() }
//
//        // 3. emit لودینگ با داده‌های کش شده (تا UI محتوای قبلی را ببیند)
//        emit(Resource.Loading(data = cachedWords))
//
//        // 4. دریافت داده از راه دور
//        val remoteResult = runCatching { api.getWordInfo(word) }
//
//        remoteResult.fold(
//            onSuccess = { remoteWords ->
//                // 5. ذخیره در دیتابیس (احتمال خطا در دیتابیس)
//                val dbResult = runCatching {
//                    dao.deleteWordInfos(remoteWords.map { it.word })
//                    dao.insertWordInfos(remoteWords.map { it.toWordInfoEntity() })
//                }
//
//                dbResult.onFailure { dbException ->
//                    // اگر خطای دیتابیس رخ داد، آن را emit می‌کنیم و از ادامه بازمی‌گردیم
//                    emit(Resource.Error(
//                        message = "Database error: ${dbException.message}",
//                        data = cachedWords
//                    ))
//                    return@flow
//                }
//
//                // 6. خواندن مجدد داده‌های جدید از دیتابیس
//                val newWords = runCatching {
//                    dao.getWordInfos(word).map { it.toWordInfo() }
//                }.getOrElse { cachedWords }
//
//                // 7. emit موفقیت
//                emit(Resource.Success(newWords))
//            },
//            onFailure = { exception ->
//                // خطاهای شبکه یا تبدیل JSON
//                val errorMessage = when (exception) {
//                    is HttpException -> "Oops, something went wrong!"
//                    is IOException -> "Couldn't reach server, check your internet connection."
//                    else -> "Unexpected error: ${exception.message}"
//                }
//                emit(Resource.Error(message = errorMessage, data = cachedWords))
//            }
//        )
//    }
//}