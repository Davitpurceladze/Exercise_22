package com.example.exercise_22.presentation.screen.postDetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.exercise_22.data.common.Resource
import com.example.exercise_22.domain.usecase.post_details.GetPostDetailsUseCase
import com.example.exercise_22.presentation.events.post_details.PostDetailsEvents
import com.example.exercise_22.presentation.mapper.post_details.toPresenter
import com.example.exercise_22.presentation.state.post_details.PostDetailsState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostDetailsViewModel @Inject constructor(
    private val getPostDetailsUseCase: GetPostDetailsUseCase
): ViewModel() {

    private val _postDetailsState = MutableStateFlow(PostDetailsState())
    val postDetailsState: SharedFlow<PostDetailsState> = _postDetailsState.asStateFlow()


    fun onEvent(events: PostDetailsEvents){
        when(events) {
            is PostDetailsEvents.FetchPostDetails -> fetchPostDetails(events.id)
        }
    }

    private fun fetchPostDetails(id: String){
        viewModelScope.launch {
            getPostDetailsUseCase(id).collect{
                when(it) {
                    is Resource.Loading -> _postDetailsState.update { currentState ->
                        currentState.copy(
                            isLoading = it.loading
                        )
                    }

                    is Resource.Error -> _postDetailsState.update { currentState ->
                        currentState.copy(
                            errorMessage = it.errorMessage
                        )
                    }

                    is Resource.Success -> _postDetailsState.update { currentState ->
                        currentState.copy(
                            postDetails = it.data.toPresenter()
                        )
                    }
                }
            }
        }
    }
}