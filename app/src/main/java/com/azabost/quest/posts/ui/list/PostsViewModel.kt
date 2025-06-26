package com.azabost.quest.posts.ui.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.azabost.quest.logging.Logger
import com.azabost.quest.posts.model.Post
import com.azabost.quest.posts.model.PostsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostsViewModel @Inject constructor(
    private val repository: PostsRepository,
    private val logger: Logger,
) : ViewModel() {
    private val _uiState = MutableStateFlow<UiState>(UiState.Loading)
    val uiState: StateFlow<UiState> = _uiState

    sealed class UiState {
        object Loading: UiState()
        data class Success(val posts: List<Post>): UiState()
        object Error: UiState()
    }

    fun getPosts() {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            try {
                val posts = repository.getPosts()
                logger.log("PostsViewModel", "getPosts: ${posts.size} posts")
                _uiState.value = UiState.Success(posts)
            } catch (e: Exception) {
                ensureActive()
                _uiState.value = UiState.Error
            }
        }
    }
}
