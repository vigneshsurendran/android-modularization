package com.azabost.quest.posts.ui.list

import com.azabost.quest.coroutines.MainDispatcherRule
import com.azabost.quest.logging.TestRecordingLoggerFactory
import com.azabost.quest.posts.model.Post
import com.azabost.quest.posts.model.PostsRepository
import io.kotest.inspectors.forAny
import io.kotest.matchers.equals.shouldBeEqual
import io.kotest.matchers.string.shouldContain
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test

class PostsViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private var posts = listOf<Post>()
    private val postsRepository = object : PostsRepository {
        override suspend fun getPosts(): List<Post> = posts
        override suspend fun getPost(id: Int): Post? = posts.find { it.id == id }
    }

    private val loggerFactory = TestRecordingLoggerFactory()

    private val viewModel = PostsViewModel(postsRepository, loggerFactory)

    @Test
    fun `given 1 post is in repository, when getPosts is invoked, then success is posted and debug log is recorded`() = runTest {
        val singlePost = listOf(Post(1, "username", "title", "body"))
        posts = singlePost

        viewModel.getPosts()
        advanceUntilIdle()

        viewModel.uiState.value.shouldBeEqual(PostsViewModel.UiState.Success(singlePost))

        loggerFactory.debugMessages.forAny {
            it.message.shouldContain("Fetched 1 posts")
        }
    }
}