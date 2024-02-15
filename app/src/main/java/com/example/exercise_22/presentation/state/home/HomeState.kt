package com.example.exercise_22.presentation.state.home

import com.example.exercise_22.presentation.model.post.Post
import com.example.exercise_22.presentation.model.story.Story

data class HomeState(

    val stories: List<Story>? = null,
    val posts: List<Post>? = null,
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
)
