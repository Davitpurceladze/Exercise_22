package com.example.exercise_22.presentation.screen.postDetails

import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.RecyclerView
import com.example.exercise_22.databinding.FragmentPostDetailsBinding
import com.example.exercise_22.presentation.base.BaseFragment
import com.example.exercise_22.presentation.events.post_details.PostDetailsEvents
import com.example.exercise_22.presentation.extension.loadImage
import com.example.exercise_22.presentation.model.post_details.PostDetailsImage
import com.example.exercise_22.presentation.state.post_details.PostDetailsState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PostDetailsFragment : BaseFragment<FragmentPostDetailsBinding>(FragmentPostDetailsBinding::inflate) {

    private val viewModel: PostDetailsViewModel by viewModels()
    private lateinit var pagerAdapter: PagerViewAdapter

    private val args: PostDetailsFragmentArgs by navArgs()



    override fun bind() {

        fetchPostDetails()

        bindViewPager()
    }

    override fun bindViewActionListeners() {
    }

    override fun bindObserves() {
        viewLifecycleOwner.lifecycleScope.launch{
            repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.postDetailsState.collect{
                    handlePostDetailsState(it)
                }
            }
        }
    }

    private fun bindViewPager() {
        pagerAdapter = PagerViewAdapter()

        with(binding.viewPager2) {
            adapter = pagerAdapter
            offscreenPageLimit = 3
            clipToPadding=false
            clipChildren = false
            getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER

        }
    }

    private fun handlePostDetailsState(state: PostDetailsState) {
        state.postDetails?.let {
            with(binding){
                val fullName = "${it.firstName} ${it.lastName}"
                tvFullName.text = fullName
                tvPostDate.text = it.postDate
                tvDescription.text = it.shareContent
                imgProfile.loadImage(it.profile)
            }
            val imgList: List<PostDetailsImage> = listOf(PostDetailsImage(1, it.images!![0]), PostDetailsImage(2, "https://cdn0.thedailyeco.com/en/posts/6/4/0/natural_regions_definition_and_examples_46_orig.jpg"),PostDetailsImage(3, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTuFLT26U49p0XhoQLNSBodEqpF2nk-Zb2zXw&usqp=CAU") )
            pagerAdapter. submitList(imgList)
        }
    }

    private fun fetchPostDetails() {
        viewModel.onEvent(PostDetailsEvents.FetchPostDetails(args.id.toString()))
    }
}