package com.azabost.quest.time

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TimeModule {

    @Provides
    @Singleton
    fun nowProvider(): NowProvider = object : NowProvider {
        override fun now(): Long = System.currentTimeMillis()
    }
}