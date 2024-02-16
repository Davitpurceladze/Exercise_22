package com.example.exercise_22.presentation.events.post_details

sealed class PostDetailsEvents {
    data class FetchPostDetails(val id:String) : PostDetailsEvents()
}