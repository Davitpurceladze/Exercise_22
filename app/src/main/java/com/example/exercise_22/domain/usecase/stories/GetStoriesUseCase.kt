package com.example.exercise_22.domain.usecase.stories

import com.example.exercise_22.domain.repository.stories.StoriesRepository
import javax.inject.Inject

class GetStoriesUseCase @Inject constructor(
    private val storiesRepository: StoriesRepository
) {
    suspend operator fun invoke() = storiesRepository.fetchStories()
}