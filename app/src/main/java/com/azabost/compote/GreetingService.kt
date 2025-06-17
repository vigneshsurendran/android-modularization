package com.azabost.compote

interface GreetingService {
    fun getGreeting(name: String): String
}

class DefaultGreetingService : GreetingService {
    override fun getGreeting(name: String): String {
        return "Hello, $name! (from injected service)"
    }
}
