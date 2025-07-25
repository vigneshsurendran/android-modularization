package com.quest.post.data.remote

import com.quest.post.data.remote.model.PostsResponse
import com.quest.post.data.remote.model.UsersResponse
import retrofit2.http.GET

interface PostsService {

    @GET("posts")
    suspend fun getPosts(): PostsResponse

    @GET("users")
    suspend fun getUsers(): UsersResponse

}