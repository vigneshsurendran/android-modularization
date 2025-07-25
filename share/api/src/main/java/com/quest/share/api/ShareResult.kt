package com.quest.share.api

sealed interface ShareResult {
    object Success : ShareResult
    class Failure(val reason: Throwable) : ShareResult
}