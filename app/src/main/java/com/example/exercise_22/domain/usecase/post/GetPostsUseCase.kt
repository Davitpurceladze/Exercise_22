package com.example.exercise_22.domain.usecase.post

import com.example.exercise_22.domain.repository.posts.PostsRepository
import javax.inject.Inject

class GetPostsUseCase @Inject constructor(
    private val postsRepository: PostsRepository
) {
    suspend operator fun invoke() = postsRepository.fetchPosts()
}