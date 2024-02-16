package com.example.exercise_22.data.service.post_details

import com.example.exercise_22.data.model.post.PostDto
import com.example.exercise_22.presentation.model.post.Post
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Query

interface PostDetailsService {
    @GET("d02b76bb-095d-45fa-90e1-dc4733d1f247")
    suspend fun getPostDetails(@Query("id") id:String) : Response<PostDto>
}