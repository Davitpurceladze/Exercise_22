package com.example.exercise_22.data.repository.post_details

import com.example.exercise_22.data.common.HandleResponse
import com.example.exercise_22.data.common.Resource
import com.example.exercise_22.data.mapper.base.asResource
import com.example.exercise_22.data.mapper.post_details.toDomain
import com.example.exercise_22.data.service.post_details.PostDetailsService
import com.example.exercise_22.domain.model.post_details.GetPostDetails
import com.example.exercise_22.domain.repository.post_details.PostDetailsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PostDetailsRepositoryImpl @Inject constructor(
    private val handleResponse: HandleResponse,
    private val postDetailsService: PostDetailsService
) : PostDetailsRepository {
    override suspend fun fetchPostDetails(id: String): Flow<Resource<GetPostDetails>> {
      return   handleResponse.safeApiCall {
             postDetailsService.getPostDetails(id = id)
         }.asResource {
             it.toDomain()
         }
    }
}