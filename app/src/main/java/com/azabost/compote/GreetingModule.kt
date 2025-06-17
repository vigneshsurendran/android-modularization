package com.azabost.compote

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface GreetingModule {

    @Binds
    fun provideGreetingService(defaultGreetingService: DefaultGreetingService): GreetingService
}
