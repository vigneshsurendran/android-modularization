package com.quest.share.impl

import android.content.Intent
import com.quest.share.api.ShareResult
import com.quest.share.api.ShareSender
import com.quest.share.api.SharingActivityHolder
import javax.inject.Inject

class ShareSenderImpl @Inject constructor(
    private val sharingActivityHolder: SharingActivityHolder,
) : ShareSender {

    override fun share(text: String): ShareResult {
        val activity = sharingActivityHolder.getSharingActivity()
            ?: return ShareResult.Failure(IllegalStateException("No sharing activity"))

        return try {
            val intent = createShareTextIntent(text)
            activity.startActivity(intent)
            ShareResult.Success
        } catch (e: Exception) {
            ShareResult.Failure(e)
        }
    }

    private fun createShareTextIntent(text: String): Intent =
        Intent.createChooser(
            Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, text)
                type = "text/plain"
            },
            null,
        )
}