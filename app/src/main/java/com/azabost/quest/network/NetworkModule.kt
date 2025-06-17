package com.azabost.quest.network

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun converterFactory(json: Json) = json.asConverterFactory("application/json".toMediaType())

    @Provides
    @Singleton
    fun okHttpClient(): OkHttpClient = OkHttpClient()
}