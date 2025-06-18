package com.azabost.quest.posts.ui.list

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
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
import com.azabost.quest.posts.model.Post
import com.azabost.quest.ui.theme.QuestTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PostsActivity : ComponentActivity() {

    private val viewModel: PostsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        viewModel.getPosts()

        setContent {
            QuestTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val posts = viewModel.posts.collectAsState()
                    LazyColumn(modifier = Modifier.padding(innerPadding)) {
                        posts.value.forEach { post ->
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
fun Post(post: Post, modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Text(text = post.userName)
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
            post = Post(1, "User", "Title", "Body"),
            modifier = Modifier
                .padding(innerPadding)
        )
    }
}
