package com.azabost.quest.posts.ui.details

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.azabost.quest.posts.model.Post
import com.azabost.quest.ui.theme.QuestTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PostDetailsActivity : ComponentActivity() {

    companion object {
        const val EXTRA_POST_ID = "extra_post_id"
    }

    private val viewModel: PostDetailsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        viewModel.fetchPost()

        setContent {
            QuestTheme {
                PostDetailsScreen(viewModel = viewModel)
            }
        }
    }
}

@Composable
fun PostDetailsScreen(
    modifier: Modifier = Modifier,
    viewModel: PostDetailsViewModel
) {
    val uiState = viewModel.uiState.collectAsState().value

    Scaffold(modifier = modifier.fillMaxSize()) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentAlignment = Alignment.Center
        ) {
            when (uiState) {
                is PostDetailsViewModel.UiState.Loading -> {
                    Text("Loading...")
                }
                is PostDetailsViewModel.UiState.PostDetails -> {
                    val post = uiState.post
                    Text("Post #${post.id}: ${post.title}\n\n${post.body}")
                }
                is PostDetailsViewModel.UiState.Error -> {
                    androidx.compose.foundation.layout.Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = androidx.compose.foundation.layout.Arrangement.Center
                    ) {
                        Text("Failed to load post #${uiState.postId}")
                        androidx.compose.material3.Button(
                            onClick = { viewModel.fetchPost() }
                        ) {
                            Text("Retry")
                        }
                    }
                }
            }
        }
    }
}
