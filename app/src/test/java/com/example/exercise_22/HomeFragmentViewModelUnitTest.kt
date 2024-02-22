package com.example.exercise_22

import com.example.exercise_22.data.common.Resource
import com.example.exercise_22.domain.model.post.GetOwner
import com.example.exercise_22.domain.model.post.GetPost
import com.example.exercise_22.domain.model.story.GetStory
import com.example.exercise_22.domain.usecase.post.GetPostsUseCase
import com.example.exercise_22.domain.usecase.story.GetStoriesUseCase
import com.example.exercise_22.presentation.events.home.HomeEvents
import com.example.exercise_22.presentation.mapper.posts.toPresenter
import com.example.exercise_22.presentation.mapper.stories.toPresenter
import com.example.exercise_22.presentation.screen.home.HomeViewModel
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
class HomeFragmentViewModelUnitTest {

    @Mock
    private lateinit var viewModel: HomeViewModel

    @Mock
    private lateinit var getPostsUseCase: GetPostsUseCase

    @Mock
    private lateinit var getStoriesUseCase: GetStoriesUseCase

    @Before
    fun setUp() {
        Dispatchers.setMain(StandardTestDispatcher())
        MockitoAnnotations.openMocks(this)

        viewModel = HomeViewModel(getStoriesUseCase, getPostsUseCase)
    }


    @Test
    fun `get story when FetchStories event is passed`() = runTest {

//        Given
        whenever(getStoriesUseCase()).thenReturn(flowOf(Resource.Success(getStoriesUseCaseMock())))

//        When
        viewModel.onEvent(HomeEvents.FetchStories)

//        Then
        val result = viewModel.homeState.take(2).toList()
        assertTrue(
            "Check if list of stories matches list of HomeState's stories",
            result.any {
                it.stories == getStoriesUseCaseMock().map {
                    it.toPresenter()
                } && it.errorMessage == null
            }
        )

    }

    @Test
    fun `get post when FetchPosts event is passed`() = runTest {
//        Given
        whenever(getPostsUseCase()).thenReturn(flowOf(Resource.Success(getPostsUseCaseMock())))

//        When
        viewModel.onEvent(HomeEvents.FetchPosts)

//        Then
        val result = viewModel.homeState.take(2).toList()
        assertTrue(
            "Check if list of posts matches list of HomeState's posts",
            result.any {
                it.posts == getPostsUseCaseMock().map {
                    it.toPresenter()
                }
            }
        )
    }

    @Test
    fun `get loader when FetchPosts event is in progress`() = runTest {
//        Given
        val loaderStatus = true
        whenever(getPostsUseCase()).thenReturn(flowOf(Resource.Loading(loaderStatus)))

//        when
        viewModel.onEvent(HomeEvents.FetchPosts)

//        Then
        val result = viewModel.homeState.take(2).toList()
        assertTrue(
            "Check if FetchPosts loader status matches HomeState's loader status",
            result.any {
                it.isLoading == loaderStatus
            }
        )
    }

    @Test
    fun `get loader when FetchStories event is in progress`() = runTest {
//      Given
        val loaderStatus = true
        whenever(getStoriesUseCase()).thenReturn(flowOf(Resource.Loading(loaderStatus)))

//        When
        viewModel.onEvent(HomeEvents.FetchStories)

//        Then
        val result = viewModel.homeState.take(2).toList()
        assertTrue(
            "Check if FetchStories loader status matches HomeState's loader status",
            result.any{
                it.isLoading ==loaderStatus
            }
        )
    }

    @Test
    fun `FetchStories returns errorMessage`() = runTest {
//        Given
        val errorMessage = "error"
        whenever(getStoriesUseCase()).thenReturn(flowOf(Resource.Error(errorMessage)))

//        When
        viewModel.onEvent(HomeEvents.FetchStories)

//        Then
        val result = viewModel.homeState.take(2).toList()
        assertTrue(
            "Check error message",
            result.any{
                it.errorMessage == errorMessage
            }
        )
    }

    @Test
    fun `FetchPosts return errorMessage`() = runTest {
//        Given
        val errorMessage = "error"
        whenever(getPostsUseCase()).thenReturn(flowOf(Resource.Error(errorMessage)))

//        when
        viewModel.onEvent(HomeEvents.FetchPosts)

//        Then
        val result = viewModel.homeState.take(2).toList()
        assertTrue(
            "Check error message",
            result.any{
                it.errorMessage == errorMessage
            }
        )
    }

    private fun getStoriesUseCaseMock(): List<GetStory> = listOf(
        GetStory(
            cover = "image", id = 1, title = "title 1"
        )
    )

    private fun getPostsUseCaseMock(): List<GetPost> = listOf(
        GetPost(
            id = 1,
            images = listOf("wdwa", "awdaw"),
            title = "title 1",
            comments = 22,
            likes = 3,
            shareContent = "",
            owner = GetOwner(
                firstName = "Dato",
                lastName = "Purceladze",
                profile = null,
                postDate = 1644671756
            )
        )
    )

    @After
    fun tearDownMainDispatcher() {
        Dispatchers.resetMain()
    }


}