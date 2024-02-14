package com.example.exercise_22.presentation.screen.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.exercise_22.databinding.ItemStoryLayoutBinding
import com.example.exercise_22.presentation.extension.loadImage
import com.example.exercise_22.presentation.model.story.Story

class StoriesRecyclerAdapter :
    ListAdapter<Story, StoriesRecyclerAdapter.StoryViewHolder>(StoriesDiffUtil()) {

    inner class StoryViewHolder(private val binding: ItemStoryLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
            private lateinit var item: Story
        fun bind() {
            item = currentList[adapterPosition]
            with(binding){
                imgPost.loadImage(item.cover)
                tvTitle.text = item.title
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = StoryViewHolder(
        ItemStoryLayoutBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: StoryViewHolder, position: Int) {
        holder.bind()
    }

    class StoriesDiffUtil : DiffUtil.ItemCallback<Story>() {
        override fun areItemsTheSame(oldItem: Story, newItem: Story): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Story, newItem: Story): Boolean {
            return oldItem == newItem
        }
    }
}