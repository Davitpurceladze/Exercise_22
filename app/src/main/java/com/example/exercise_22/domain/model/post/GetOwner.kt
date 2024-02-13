package com.example.exercise_22.domain.model.post

import com.squareup.moshi.Json

data class GetOwner (
    val firstName: String,
    val lastName: String,
    val profile: String?,
    val postDate: Int
)