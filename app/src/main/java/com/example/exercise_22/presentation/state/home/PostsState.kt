package com.example.exercise_22.presentation.state.home

import com.example.exercise_22.presentation.model.post.Post

data class PostsState (
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val posts: List<Post>? = null,
)