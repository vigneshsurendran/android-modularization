package com.azabost.quest.posts.di

import com.azabost.quest.posts.model.PostsRepository
import com.azabost.quest.posts.remote.PostsService
import com.azabost.quest.posts.remote.RemotePostsRepository
import dagger.Binds
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
interface PostsModule {

    @Binds
    fun postsRepository(repository: RemotePostsRepository): PostsRepository

    companion object {
        @Provides
        @Singleton
        fun postsService(okHttpClient: OkHttpClient, converterFactory: Converter.Factory): PostsService =
            Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(converterFactory)
                .build()
                .create(PostsService::class.java)
    }
}