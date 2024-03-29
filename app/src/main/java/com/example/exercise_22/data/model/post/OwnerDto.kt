package com.example.exercise_22.data.model.post

import com.squareup.moshi.Json

data class OwnerDto(
    @Json(name = "first_name")
    val firstName: String,
    @Json(name = "last_name")
    val lastName: String,
    val profile: String?,
    @Json(name = "post_date")
    val postDate: Int
)


