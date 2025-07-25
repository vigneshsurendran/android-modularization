package com.quest.share.api

interface ShareSender {
    fun share(text: String): ShareResult
}