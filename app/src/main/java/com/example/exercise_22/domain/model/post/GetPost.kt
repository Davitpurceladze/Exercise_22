package com.example.exercise_22.domain.model.post

import com.squareup.moshi.Json

data class GetPost (
    val id: Int,
    val images: List<String>?,
    val title: String,
    val comments: Int,
    val likes: Int,
    val shareContent: String,
    val owner: GetOwner
)