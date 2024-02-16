package com.example.exercise_22.data.mapper.post_details

import com.example.exercise_22.data.model.post.PostDto
import com.example.exercise_22.domain.model.post_details.GetPostDetails

fun PostDto.toDomain() = GetPostDetails(
    id = id, profile = owner.profile, firstName = owner.firstName, lastName = owner.lastName, postDate = owner.postDate, shareContent = shareContent, images = images

)