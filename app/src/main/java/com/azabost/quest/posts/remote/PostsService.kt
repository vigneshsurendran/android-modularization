package com.azabost.quest.posts.remote

import com.azabost.quest.posts.remote.model.PostsResponse
import retrofit2.http.GET

interface PostsService {

    @GET("posts")
    suspend fun getPosts(): PostsResponse


}