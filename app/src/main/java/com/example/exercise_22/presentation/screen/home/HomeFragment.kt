package com.example.exercise_22.presentation.screen.home

import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.exercise_22.databinding.FragmentHomeBinding
import com.example.exercise_22.presentation.base.BaseFragment
import com.example.exercise_22.presentation.events.home.HomeEvents
import com.example.exercise_22.presentation.state.home.PostsState
import com.example.exercise_22.presentation.state.home.StoriesState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {

    private val viewModel: HomeViewModel by viewModels()

    override fun bind() {
        fetchData()
    }

    override fun bindViewActionListeners() {
    }

    override fun bindObserves() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.storiesState.collect{
                     handleStoriesSate(it)
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.postsState.collect{
                    handlePostsState(it)
                }
            }
        }
    }

    private fun handlePostsState(state: PostsState) {
        state.posts?.let {
            println("this is posts in home fragment -> $it")
        }
    }
    private fun handleStoriesSate(state: StoriesState){
        state.stories?.let {
            println("this is stories in home fragment -> $it")
        }
    }


    private fun fetchData(){
        viewModel.apply {
            onEvent(HomeEvents.FetchStories)
            onEvent(HomeEvents.FetchPosts)
        }

    }
}