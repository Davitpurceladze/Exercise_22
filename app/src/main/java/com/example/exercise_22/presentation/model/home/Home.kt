package com.example.exercise_22.presentation.model.home

import com.example.exercise_22.presentation.model.post.Post
import com.example.exercise_22.presentation.model.story.Story

data class Home(
    val id : Int,
    val stories: List<Story> ,
    val posts: List<Post>   ,
)