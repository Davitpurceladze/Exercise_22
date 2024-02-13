package com.example.exercise_22.presentation.screen.home

import androidx.fragment.app.viewModels
import com.example.exercise_22.databinding.FragmentHomeBinding
import com.example.exercise_22.presentation.base.BaseFragment
import com.example.exercise_22.presentation.events.home.HomeEvents
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {

    private val viewModel: HomeViewModel by viewModels()

    override fun bind() {
        fetchData()
    }

    override fun bindViewActionListeners() {
    }

    override fun bindObserves() {
    }

    private fun fetchData(){
        viewModel.apply {
            onEvent(HomeEvents.FetchStories)
            onEvent(HomeEvents.FetchPosts)
        }

    }
}