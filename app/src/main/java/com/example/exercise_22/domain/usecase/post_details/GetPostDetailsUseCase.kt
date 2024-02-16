package com.example.exercise_22.domain.usecase.post_details

import com.example.exercise_22.domain.repository.post_details.PostDetailsRepository
import javax.inject.Inject

class GetPostDetailsUseCase @Inject constructor(
    private val postDetailsRepository: PostDetailsRepository
) {
    suspend operator fun invoke(id:String) = postDetailsRepository.fetchPostDetails(id =id )
}