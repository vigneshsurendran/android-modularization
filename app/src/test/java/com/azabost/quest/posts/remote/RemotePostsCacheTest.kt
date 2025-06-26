package com.azabost.quest.posts.remote

import com.azabost.quest.posts.model.Post
import com.azabost.quest.time.FakeNowProvider
import org.junit.Assert.assertEquals
import org.junit.Test
import kotlin.time.Duration.Companion.hours

class RemotePostsCacheTest {

    private val nowProvider = FakeNowProvider()
    private val cacheExpiryTimeMillis: Long = 1.hours.inWholeMilliseconds
    private val cache = RemotePostsCache(cacheExpiryTimeMillis, nowProvider)

    private val post = Post(1, "username", "title", "body")

    @Test
    fun `test cache not expired`() {
        val posts = listOf(post)
        cache.store(posts)
        assertEquals(posts, cache.get())
    }

    @Test
    fun `test cache expired`() {
        val posts = listOf(post)
        cache.store(posts)
        advanceTimeToExpireCache()
        assertEquals(null, cache.get())
    }

    private fun advanceTimeToExpireCache() {
        nowProvider.advanceTimeBy(cacheExpiryTimeMillis + 1)
    }

}