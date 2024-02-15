package com.example.exercise_22.presentation.mapper.posts

import com.example.exercise_22.domain.model.post.GetPost
import com.example.exercise_22.presentation.extension.realTimeConvert
import com.example.exercise_22.presentation.model.post.Owner
import com.example.exercise_22.presentation.model.post.Post
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun GetPost.toPresenter() = Post(
    id = id, images = images, title = title, comments = comments, likes = likes, shareContent = shareContent, owner = Owner(
        firstName = owner.firstName,
        lastName = owner.lastName,
        profile = owner.profile,
        postDate = realTimeConvert( owner.postDate)
    )
)

