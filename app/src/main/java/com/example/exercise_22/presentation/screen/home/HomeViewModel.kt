package com.example.exercise_22.presentation.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.exercise_22.data.common.Resource
import com.example.exercise_22.domain.usecase.post.GetPostsUseCase
import com.example.exercise_22.domain.usecase.story.GetStoriesUseCase
import com.example.exercise_22.presentation.events.home.HomeEvents
import com.example.exercise_22.presentation.mapper.posts.toPresenter
import com.example.exercise_22.presentation.mapper.stories.toPresenter
import com.example.exercise_22.presentation.state.home.HomeState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getStoriesUseCase: GetStoriesUseCase,
    private val getPostsUseCase: GetPostsUseCase
) : ViewModel() {

    private val _homeState = MutableStateFlow(HomeState())
    val homeState: SharedFlow<HomeState> = _homeState.asStateFlow()

    fun onEvent(events: HomeEvents) {
        when (events) {
            is HomeEvents.FetchStories -> fetchStories()
            is HomeEvents.FetchPosts -> fetchPosts()
        }
    }

    private fun fetchStories() {
        viewModelScope.launch {
            getStoriesUseCase().collect {
                when (it) {
                    is Resource.Loading -> _homeState.update { currentState ->
                        currentState.copy(
                            isLoading = it.loading
                        )
                    }

                    is Resource.Error -> _homeState.update { currentState ->
                        currentState.copy(
                            errorMessage = it.errorMessage
                        )
                    }

                    is Resource.Success -> {
                        _homeState.update { currentState ->
                            currentState.copy(
                                stories = it.data.map {
                                    it.toPresenter()
                                }
                            )
                        }
                    }
                }
            }
        }
    }

    private fun fetchPosts() {
        viewModelScope.launch {
            getPostsUseCase().collect {
                when (it) {
                    is Resource.Loading -> _homeState.update { currentState ->
                        currentState.copy(
                            isLoading = it.loading
                        )
                    }

                    is Resource.Error -> _homeState.update { currentState ->
                        currentState.copy(
                            errorMessage = it.errorMessage
                        )
                    }

                    is Resource.Success -> {
                        _homeState.update { currentState ->
                            currentState.copy(
                                posts = it.data.map{
                                    it.toPresenter()
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}