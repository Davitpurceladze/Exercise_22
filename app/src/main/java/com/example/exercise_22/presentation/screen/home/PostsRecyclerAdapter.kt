package com.example.exercise_22.presentation.screen.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.exercise_22.databinding.ItemPostLayoutBinding
import com.example.exercise_22.presentation.extension.loadImage
import com.example.exercise_22.presentation.model.post.Post
import com.example.exercise_22.presentation.model.story.Story

class PostsRecyclerAdapter: ListAdapter<Post, PostsRecyclerAdapter.PostViewHolder>(PostsDiffUtil()) {

    private var onItemClickListener: ((id: Int) -> Unit)? = null

    fun setOnItemClickListener(listener: (id: Int) -> Unit)  {
        onItemClickListener = listener
    }

    inner class PostViewHolder(private val binding: ItemPostLayoutBinding): RecyclerView.ViewHolder(binding.root){
       private lateinit var item: Post

        fun bind() {
            item = currentList[adapterPosition]
            with(binding){
                root.setOnClickListener {
                    onItemClickListener?.invoke(item.id)
                }
                val fullName = "${item.owner.firstName} ${item.owner.lastName}"
                tvFullName .text = fullName
                tvDescription.text = item.shareContent
                tvCommentAmount.text=item.comments.toString()
                tvLikesAmount.text=item.likes.toString()
                tvPostDate.text = item.owner.postDate

                when( item.images?.size) {
                    null -> {
                        imgPost1.visibility = View.GONE
                        imgPost2.visibility = View.GONE
                        imgPost3.visibility = View.GONE
                    }

                    1 -> {
                        imgPost1.loadImage(item.images!![0])
                        imgPost2.visibility = View.GONE
                        imgPost3.visibility = View.GONE
                    }

                    2 -> {
                        imgPost1.loadImage(item.images!![0])
                        imgPost2.loadImage(item.images!![1])
                        imgPost3.visibility = View.GONE
                    }

                    3-> {
                        imgPost1.loadImage(item.images!![0])
                        imgPost2.loadImage(item.images!![1])
                        imgPost3.loadImage(item.images!![2])
                    }
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = PostViewHolder(
        ItemPostLayoutBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.bind()
    }

    class PostsDiffUtil : DiffUtil.ItemCallback<Post>() {
        override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean {
            return oldItem == newItem
        }
    }
}