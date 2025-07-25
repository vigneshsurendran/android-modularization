package com.quest.post.data.remote

import com.quest.post.domain.Post
import com.quest.post.domain.PostsRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemotePostsRepository @Inject constructor(
    private val postsService: PostsService,
    private val cache: RemotePostsCache
) : PostsRepository {

    override suspend fun getPosts(): List<Post> = coroutineScope {
        val usersAsync = async { postsService.getUsers() }
        val postsAsync = async { postsService.getPosts() }

        postsAsync.await()
            .map { post ->
                val user = usersAsync.await().first { it.id == post.userId }
                Post(
                    id = post.id,
                    userName = user.name,
                    title = post.title,
                    body = post.body
                )
            }.also { cache.store(it) }
    }

    override suspend fun getPost(id: Int): Post? =
        cache.get()
            ?.find { it.id == id }
            ?: getPosts()
                .find { it.id == id }

}