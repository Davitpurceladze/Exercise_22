package com.example.exercise_22.data.service.stories

import com.example.exercise_22.data.model.story.StoryDto
import retrofit2.Response
import retrofit2.http.GET

interface StoriesService {

    @GET("1e2c42be-fc82-4efb-9f3f-4ce4ce80743c")
    suspend fun getStories(): Response<List<StoryDto>>
}