package com.example.exercise_22.domain.repository.post_details

import com.example.exercise_22.data.common.Resource
import com.example.exercise_22.domain.model.post_details.GetPostDetails
import kotlinx.coroutines.flow.Flow

interface PostDetailsRepository {

    suspend fun fetchPostDetails(id: String) : Flow<Resource<GetPostDetails>>
}