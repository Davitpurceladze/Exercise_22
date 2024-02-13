package com.example.exercise_22.presentation.state.home

import com.example.exercise_22.presentation.model.story.Story

data class StoriesState (
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val stories: List<Story>? = null

)