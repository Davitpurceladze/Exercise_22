package com.example.exercise_22.domain.repository.posts

import com.example.exercise_22.data.common.Resource
import com.example.exercise_22.domain.model.post.GetPost
import kotlinx.coroutines.flow.Flow

interface PostsRepository {
    suspend fun fetchPosts() : Flow<Resource<List<GetPost>>>
}