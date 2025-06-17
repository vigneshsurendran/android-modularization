package com.azabost.quest.posts.di

import com.azabost.quest.posts.remote.PostsService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PostsModule {

    @Provides
    @Singleton
    fun postsService(okHttpClient: OkHttpClient, converterFactory: Converter.Factory): PostsService =
        Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(converterFactory)
            .build()
            .create(PostsService::class.java)

    @Provides
    @Singleton
    fun converterFactory(json: Json) = json.asConverterFactory("application/json".toMediaType())

    @Provides
    @Singleton
    fun okHttpClient(): OkHttpClient = OkHttpClient()


}