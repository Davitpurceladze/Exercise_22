package com.example.exercise_22.presentation.events.home

sealed class HomeEvents {
    data object FetchStories: HomeEvents()
    data object FetchPosts: HomeEvents()
}