package com.azabost.quest.logging

import android.util.Log
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet
import javax.inject.Inject
import javax.inject.Singleton

interface Logger {
    fun log(tag: String, message: String)
}

@Singleton
class CompositeLogger @Inject constructor(
    private val loggers: Set<@JvmSuppressWildcards Logger>
) : Logger {
    override fun log(tag: String, message: String) {
        loggers.forEach { it.log(tag, message) }
    }
}

@Singleton
class SentryLogger @Inject constructor() : Logger {
    override fun log(tag: String, message: String) {
        println("[$tag] $message")

    }
}



@Module
@InstallIn(SingletonComponent::class)
internal interface LoggingModule {
    @Binds
    fun compositeLogger(compositeLogger: CompositeLogger): Logger
}

@Singleton
class LogcatLogger @Inject constructor() : Logger {
    override fun log(tag: String, message: String) {
        Log.i(tag, message)
    }
}

@Module
@InstallIn(SingletonComponent::class)
internal interface LogcatModule {
    @Binds
    @IntoSet
    fun logcatLogger(logcatLogger: LogcatLogger): Logger
}

@Module
@InstallIn(SingletonComponent::class)
internal interface SentryModule {
    @Binds
    @IntoSet
    fun sentryLogger(sentryLogger: SentryLogger): Logger
}