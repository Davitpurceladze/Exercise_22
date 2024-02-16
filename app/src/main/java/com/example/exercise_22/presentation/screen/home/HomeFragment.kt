package com.example.exercise_22.presentation.screen.home

import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.exercise_22.databinding.FragmentHomeBinding
import com.example.exercise_22.presentation.base.BaseFragment
import com.example.exercise_22.presentation.events.home.HomeEvents
import com.example.exercise_22.presentation.model.home.Home
import com.example.exercise_22.presentation.model.post.Post
import com.example.exercise_22.presentation.model.story.Story
import com.example.exercise_22.presentation.state.home.HomeState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {

    private val viewModel: HomeViewModel by viewModels()
    private lateinit var homeRecyclerAdapter: HomeRecyclerAdapter
    private var storiesList: List<Story> = listOf()
    private var postsList: List<Post> = listOf()

    override fun bind() {
        fetchData()
        setHomeRecyclerAdapter()
    }

    override fun bindViewActionListeners() {
    }

    override fun bindObserves() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.homeState.collect{
                    handleHomeState(it)
                }
            }
        }

        itemClickListener()
    }

    private fun itemClickListener() {
        homeRecyclerAdapter.setOnItemClickListener {
            println("this is item id in fragment -> $it")
            navigateToPostDetailsFragment(it)
        }
    }

    private fun  navigateToPostDetailsFragment(id: Int) {
        findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToPostDetailsFragment(id))
    }

    private fun handleHomeState(state: HomeState) {
        if(state.posts != null && state.stories != null) {
            val list = listOf(Home(1, state.stories, state.posts ), Home(2, state.stories, state.posts))
            homeRecyclerAdapter.submitList(list)
        }
    }
    private fun setHomeRecyclerAdapter() {
        homeRecyclerAdapter = HomeRecyclerAdapter()
        with(binding.homeRecycler) {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = homeRecyclerAdapter
        }
    }

    private fun fetchData() {
        viewModel.apply {
            onEvent(HomeEvents.FetchStories)
            onEvent(HomeEvents.FetchPosts)
        }
    }
}