package com.quest.share.api.usecase

import com.quest.analytics.api.Analytics
import com.quest.analytics.api.AnalyticsEvent
import com.quest.logger.api.Logger
import com.quest.logger.api.create
import com.quest.post.domain.Post
import com.quest.share.api.ShareResult
import com.quest.share.api.ShareSender
import javax.inject.Inject

class SharePostUseCase @Inject constructor(
    private val shareSender: ShareSender,
    private val analytics: Analytics,
    loggerFactory: Logger.Factory,
) {
    private val logger by lazy { loggerFactory.create(this::class) }

    fun execute(post: Post) {
        val text = "${post.title}\n\n${post.body}"
        val shareResult = shareSender.share(text)
        when (shareResult) {
            ShareResult.Success -> analytics.logEvent(AnalyticsEvent.POST_SHARED)
            is ShareResult.Failure -> logger.error("Failed to share post", shareResult.reason)
        }
    }
}