package com.example.exercise_22.domain.model.post_details

data class GetPostDetails (
    val id: Int,
    val profile: String?,
    val firstName: String,
    val lastName: String,
    val postDate: Int,
    val shareContent:String,
    val images: List<String>?
)