package com.example.dictionary.feature_dictionary.di

import android.content.Context
import androidx.room.Room
import com.example.dictionary.feature_dictionary.data.local.WordInfoDatabase
import com.example.dictionary.feature_dictionary.data.local.WordInfoLocalDataSource
import com.example.dictionary.feature_dictionary.data.local.WordInfoLocalDataSourceImpl
import com.example.dictionary.feature_dictionary.data.remote.DictionaryApi
import com.example.dictionary.feature_dictionary.data.remote.DictionaryApi.Companion.BASE_URL
import com.example.dictionary.feature_dictionary.data.remote.WordInfoRemoteDataSource
import com.example.dictionary.feature_dictionary.data.remote.WordInfoRemoteDataSourceImpl
import com.example.dictionary.feature_dictionary.data.repository.WordInfoRepositoryImpl
import com.example.dictionary.feature_dictionary.data.util.GsonJsonParser
import com.example.dictionary.feature_dictionary.data.util.JsonParser
import com.example.dictionary.feature_dictionary.domain.repository.WordInfoRepository
import com.google.gson.Gson
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DictionaryBindingsModule {

    @Binds
    @Singleton
    abstract fun bindWordInfoRepository(
        implementation: WordInfoRepositoryImpl
    ): WordInfoRepository

    @Binds
    @Singleton
    abstract fun bindRemoteDataSource(
        implementation: WordInfoRemoteDataSourceImpl
    ): WordInfoRemoteDataSource

    @Binds
    @Singleton
    abstract fun bindLocalDataSource(
        implementation: WordInfoLocalDataSourceImpl
    ): WordInfoLocalDataSource

    @Binds
    @Singleton
    abstract fun bindJsonParser(
        implementation: GsonJsonParser
    ): JsonParser
}

@Module
@InstallIn(SingletonComponent::class)
object DictionaryProvidersModule {

    @Provides
    @Singleton
    fun provideGson(): Gson = Gson()

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideDictionaryApi(
        retrofit: Retrofit
    ): DictionaryApi {
        return retrofit.create(DictionaryApi::class.java)
    }

    @Provides
    @Singleton
    fun provideDatabase(
        context: Context
    ): WordInfoDatabase {
        return Room.databaseBuilder(
            context,
            WordInfoDatabase::class.java,
            "word_info.db"
        ).build()
    }

    @Provides
    fun provideWordInfoDao(
        database: WordInfoDatabase
    ) = database.wordInfoDao()
}
