package com.azabost.quest.posts.remote

import com.azabost.quest.posts.model.Post
import com.azabost.quest.time.NowProvider
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemotePostsCache @Inject constructor(val nowProvider: NowProvider) {
    var posts: List<Post>? = null
    private var storedAt: Long = 0

    fun store(posts: List<Post>) {
        this.posts = posts
        storedAt = nowProvider.now()
    }

    fun get(): List<Post>? {
        val isCacheExpired = storedAt + CACHE_EXPIRATION < System.currentTimeMillis()

        return if (isCacheExpired) {
            clear()
            null
        } else {
            posts
        }
    }

    fun clear() {
        posts = null
    }

    companion object {
        private const val CACHE_EXPIRATION = 1000 * 60 * 60
    }
}