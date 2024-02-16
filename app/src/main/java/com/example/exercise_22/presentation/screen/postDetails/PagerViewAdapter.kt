package com.example.exercise_22.presentation.screen.postDetails

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.exercise_22.databinding.ItemImgLayoutBinding
import com.example.exercise_22.presentation.extension.loadImage
import com.example.exercise_22.presentation.model.post_details.PostDetailsImage

class PagerViewAdapter: ListAdapter<PostDetailsImage, PagerViewAdapter.ImgViewHolder>(ImageDiffUtil()) {

    inner class ImgViewHolder(private val binding: ItemImgLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        private lateinit var item: PostDetailsImage

        fun bind() {
            item = currentList[adapterPosition]

            binding.postDetailsImg.loadImage(item.imgUrl)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)= ImgViewHolder(
        ItemImgLayoutBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: ImgViewHolder, position: Int) {
        holder.bind()
    }

    class ImageDiffUtil : DiffUtil.ItemCallback<PostDetailsImage>() {
        override fun areItemsTheSame(oldItem: PostDetailsImage, newItem: PostDetailsImage): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: PostDetailsImage, newItem: PostDetailsImage): Boolean {
            return oldItem == newItem
        }
    }
}