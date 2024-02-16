package com.example.exercise_22.presentation.state.post_details

import com.example.exercise_22.presentation.model.post_details.PostDetails

data class PostDetailsState(
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val postDetails: PostDetails? = null
)