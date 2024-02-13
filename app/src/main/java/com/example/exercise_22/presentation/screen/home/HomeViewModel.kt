package com.example.exercise_22.presentation.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.exercise_22.domain.usecase.post.GetPostsUseCase
import com.example.exercise_22.domain.usecase.story.GetStoriesUseCase
import com.example.exercise_22.presentation.events.home.HomeEvents
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getStoriesUseCase: GetStoriesUseCase,
    private val getPostsUseCase: GetPostsUseCase
): ViewModel() {

    fun onEvent(events: HomeEvents){
        when(events){
            is HomeEvents.FetchStories -> fetchStories()
            is HomeEvents.FetchPosts -> fetchPosts()
        }

    }

    private fun fetchStories() {
        viewModelScope.launch {
            getStoriesUseCase().collect{
                println("this is story in viewModel $it")
            }
        }
    }

    private fun fetchPosts() {
        viewModelScope.launch {
            getPostsUseCase().collect{
                println("this is posts in viewModel $it")
            }
        }
    }
}