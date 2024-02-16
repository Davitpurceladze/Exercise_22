package com.example.exercise_22.presentation.screen.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.exercise_22.databinding.ItemHomePostLayoutBinding
import com.example.exercise_22.databinding.ItemHomeStoryLayoutBinding
import com.example.exercise_22.presentation.model.home.Home

const val ITEM_STORY = 1
const val ITEM_POST = 2

class HomeRecyclerAdapter : ListAdapter<Home, RecyclerView.ViewHolder>(HomeDiffUtil()) {

    private var onItemClickListener: ((id: Int) -> Unit)? = null

    fun setOnItemClickListener(listener: (id: Int) -> Unit)  {
        onItemClickListener = listener
    }

    inner class StoryViewHolder(private val binding: ItemHomeStoryLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(){
            val stories = currentList[adapterPosition].stories
            with(binding.root) {
                layoutManager = LinearLayoutManager(itemView.context, RecyclerView.HORIZONTAL, false)
                adapter= StoriesRecyclerAdapter().apply {
                    submitList(stories)
                }
            }
        }
    }

    inner class PostViewHolder(private val binding: ItemHomePostLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            val posts = currentList[adapterPosition].posts
            with(binding.root){
                layoutManager = LinearLayoutManager(itemView.context)
                adapter = PostsRecyclerAdapter().apply {
                    submitList(posts)
                    setOnItemClickListener {
                        onItemClickListener?.invoke(it)
                    }
                }
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (position) {
            0 -> ITEM_STORY
            1 -> ITEM_POST
            else -> ITEM_POST
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if(viewType == ITEM_STORY) {
            StoryViewHolder(ItemHomeStoryLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false))
        } else {
            PostViewHolder(ItemHomePostLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false))
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when( holder) {
            is StoryViewHolder -> holder.bind()
            is PostViewHolder -> holder.bind()
        }
    }


    class HomeDiffUtil : DiffUtil.ItemCallback<Home>() {
        override fun areItemsTheSame(oldItem: Home, newItem: Home): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Home, newItem: Home): Boolean {
            return oldItem == newItem
        }
    }
}