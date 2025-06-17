package com.azabost.quest.posts.remote.model

import kotlinx.serialization.Serializable

typealias PostsResponse = List<PostResponseItem>

@Serializable
data class PostResponseItem(
    val id: Int,
    val userId: Int,
    val title: String,
    val body: String
)
