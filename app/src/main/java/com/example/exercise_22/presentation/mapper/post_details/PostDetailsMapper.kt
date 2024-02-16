package com.example.exercise_22.presentation.mapper.post_details

import com.example.exercise_22.domain.model.post_details.GetPostDetails
import com.example.exercise_22.presentation.extension.realTimeConvert
import com.example.exercise_22.presentation.model.post_details.PostDetails

fun GetPostDetails.toPresenter() = PostDetails(
    id = id, profile = profile, firstName = firstName, lastName = lastName, postDate = realTimeConvert( postDate), shareContent = shareContent, images = images

)