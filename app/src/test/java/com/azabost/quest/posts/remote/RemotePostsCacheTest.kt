package com.azabost.quest.posts.remote

import com.azabost.quest.posts.model.Post
import com.azabost.quest.time.FakeNowProvider
import org.junit.Assert.assertEquals
import org.junit.Test

class RemotePostsCacheTest {

    private val nowProvider = FakeNowProvider()
    private val cache = RemotePostsCache(nowProvider)

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
        nowProvider.advanceTimeBy(1000 * 60 * 60 + 1)
        assertEquals(null, cache.get())
    }

}