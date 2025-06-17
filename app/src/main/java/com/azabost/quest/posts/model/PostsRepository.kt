package com.azabost.quest.posts.model

interface PostsRepository {
    suspend fun getPosts(): List<Post>
}