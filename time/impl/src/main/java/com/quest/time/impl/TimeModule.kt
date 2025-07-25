package com.quest.time.impl

import com.quest.time.api.NowProvider
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface TimeModule {
    @Binds
    fun nowProvider(defaultNowProvider: DefaultNowProvider): NowProvider
}