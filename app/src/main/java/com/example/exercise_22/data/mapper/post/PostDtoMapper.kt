package com.example.exercise_22.data.mapper.post

import com.example.exercise_22.data.model.post.PostDto
import com.example.exercise_22.domain.model.post.GetOwner
import com.example.exercise_22.domain.model.post.GetPost

fun PostDto.toDomain() = GetPost(
    id = id, images = images, title = title, comments = comments, likes = likes, shareContent = shareContent, owner = GetOwner(
        firstName = owner.firstName,
        lastName = owner.lastName,
        profile = owner.profile,
        postDate = owner.postDate
    )
)