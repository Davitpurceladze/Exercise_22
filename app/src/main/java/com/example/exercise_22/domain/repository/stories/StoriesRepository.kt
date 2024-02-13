package com.example.exercise_22.domain.repository.stories

import com.example.exercise_22.data.common.Resource
import com.example.exercise_22.domain.model.story.GetStory
import kotlinx.coroutines.flow.Flow

interface StoriesRepository {
    suspend fun fetchStories(): Flow<Resource<List<GetStory>>>
}