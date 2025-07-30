package com.quest.logger.api

import kotlin.reflect.KClass

interface Logger {
    val name: String

    fun debug(message: String)
    fun info(message: String)
    fun error(message: String, throwable: Throwable? = null)

    interface Factory {
        fun create(name: String): Logger
    }
}

fun Logger.Factory.create(kClass: KClass<*>): Logger = create(kClass.java.simpleName)


