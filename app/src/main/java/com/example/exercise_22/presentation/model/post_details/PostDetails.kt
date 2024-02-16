package com.example.exercise_22.presentation.model.post_details

data class PostDetails(
    val id: Int,
    val profile: String?,
    val firstName: String,
    val lastName: String,
    val postDate: String,
    val shareContent:String,
    val images: List<String>?
)
