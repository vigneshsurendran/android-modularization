package com.quest.post.data.remote.model

import kotlinx.serialization.Serializable

typealias UsersResponse = List<UsersResponseItem>

@Serializable
data class UsersResponseItem(
    val id: Int,
    val name: String
)