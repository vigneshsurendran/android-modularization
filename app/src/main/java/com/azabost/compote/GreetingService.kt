package com.azabost.compote

import javax.inject.Inject
import javax.inject.Singleton

interface GreetingService {
    fun getGreeting(name: String): String
}

@Singleton
class DefaultGreetingService @Inject constructor() : GreetingService {
    override fun getGreeting(name: String): String {
        return "[$this] Hello, $name!"
    }
}
