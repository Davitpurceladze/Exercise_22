package com.example.exercise_22.presentation.model.post

data class Post (
    val id: Int,
    val images: List<String>?,
    val title: String,
    val comments: Int,
    val likes: Int,
    val shareContent: String,
    val owner: Owner
)