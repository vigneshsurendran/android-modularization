package com.azabost.compote

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object GreetingModule {

    @Provides
    @Singleton
    fun provideGreetingService(): GreetingService {
        return DefaultGreetingService()
    }
}
