package com.azabost.quest.posts

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import com.azabost.quest.posts.model.Post
import com.azabost.quest.posts.remote.PostsService
import com.azabost.quest.posts.remote.model.PostResponseItem
import com.azabost.quest.posts.remote.model.PostsResponse
import com.azabost.quest.ui.theme.QuestTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class PostsActivity : ComponentActivity() {

    @Inject
    lateinit var postsService: PostsService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val posts = MutableStateFlow<PostsResponse?>(null)

        lifecycleScope.launch { posts.value = postsService.getPosts() }

        setContent {
            QuestTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val posts = posts.collectAsState()
                    LazyColumn(modifier = Modifier.padding(innerPadding)) {
                        posts.value?.forEach { post ->
                            item {
                                Post(post = post)
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Post(post: PostResponseItem, modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Text(text = post.userId.toString())
        Text(text = post.id.toString())
        Text(text = post.title)
        Text(text = post.body)
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PostsPreview() {
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Post(
            post = PostResponseItem(1, 1, "title", "body"),
            modifier = Modifier
                .padding(innerPadding)
        )
    }
}
