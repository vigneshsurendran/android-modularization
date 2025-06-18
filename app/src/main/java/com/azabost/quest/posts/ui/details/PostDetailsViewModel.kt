package com.azabost.quest.posts.ui.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.azabost.quest.posts.model.Post
import com.azabost.quest.posts.model.PostsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostDetailsViewModel @Inject constructor(
    private val postsRepository: PostsRepository,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {
    val postId: Int = savedStateHandle.get(PostDetailsActivity.EXTRA_POST_ID)!!

    private val _uiState = MutableStateFlow<UiState>(UiState.Loading)
    val uiState: StateFlow<UiState> = _uiState

    sealed class UiState {
        object Loading: UiState()
        data class PostDetails(val post: Post): UiState()
        data class Error(val postId: Int): UiState()
    }

    fun fetchPost() = viewModelScope.launch {
        try {
            val post = postsRepository.getPost(postId)
            if (post != null) {
                showPostDetails(post)
            } else {
                showError()
            }
        } catch (e: Exception) {
            ensureActive()
            showError()
        }
    }

    private fun showPostDetails(post: Post) {
        _uiState.value = UiState.PostDetails(post)
    }

    private fun showError() {
        _uiState.value = UiState.Error(postId)
    }

}
