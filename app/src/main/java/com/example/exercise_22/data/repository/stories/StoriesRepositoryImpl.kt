package com.example.exercise_22.data.repository.stories

import com.example.exercise_22.data.common.HandleResponse
import com.example.exercise_22.data.common.Resource
import com.example.exercise_22.data.mapper.base.asResource
import com.example.exercise_22.data.mapper.stories.toDomain
import com.example.exercise_22.data.service.stories.StoriesService
import com.example.exercise_22.domain.model.story.GetStory
import com.example.exercise_22.domain.repository.stories.StoriesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class StoriesRepositoryImpl @Inject constructor(
    private val storiesService: StoriesService,
    private val handleResponse: HandleResponse
): StoriesRepository {
    override suspend fun fetchStories(): Flow<Resource<List<GetStory>>> {
       return handleResponse.safeApiCall {
            storiesService.getStories()
        }.asResource {
            it.map {
                it.toDomain()
            }
        }
    }
}