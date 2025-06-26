package com.azabost.quest.posts.ui.list

import com.azabost.quest.posts.model.Post
import com.azabost.quest.posts.model.PostsRepository

class FailingPostsRepository : PostsRepository {
    override suspend fun getPosts(): List<Post> = throw RuntimeException("Network error")
    override suspend fun getPost(id: Int): Post? = throw RuntimeException("Network error")
}