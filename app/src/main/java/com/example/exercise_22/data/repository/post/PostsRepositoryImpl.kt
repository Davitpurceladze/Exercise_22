package com.example.exercise_22.data.repository.post

import com.example.exercise_22.data.common.HandleResponse
import com.example.exercise_22.data.common.Resource
import com.example.exercise_22.data.mapper.base.asResource
import com.example.exercise_22.data.mapper.post.toDomain
import com.example.exercise_22.data.service.posts.PostsService
import com.example.exercise_22.domain.model.post.GetPost
import com.example.exercise_22.domain.repository.posts.PostsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PostsRepositoryImpl @Inject constructor(
    private val postsService: PostsService,
    private val handleResponse: HandleResponse
) : PostsRepository {
    override suspend fun fetchPosts(): Flow<Resource<List<GetPost>>> {
        return handleResponse.safeApiCall {
            postsService.getPosts()
        }.asResource {
            it.map {
                it.toDomain()
            }
        }
    }
}