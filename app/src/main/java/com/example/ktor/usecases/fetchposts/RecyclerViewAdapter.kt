package com.example.ktor.usecases.fetchposts

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ktor.data.PostsModel
import com.example.ktor.databinding.RecyclerviewItemBinding

class RecyclerViewAdapter :
    ListAdapter<PostsModel, RecyclerViewAdapter.RestaurantViewHolder>(RestaurantComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantViewHolder {
        val binding =
            RecyclerviewItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RestaurantViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RestaurantViewHolder, position: Int) {
        val currentItem = getItem(position)
        if (currentItem != null) {
            holder.bind(currentItem)
        }
    }

    class RestaurantViewHolder(private val binding: RecyclerviewItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(postsModel: PostsModel) {


            binding.apply {
                Glide.with(itemView)
                    .load(postsModel._embedded)
                    .into(imageViewLogo)

                textViewName.text = postsModel.status
                textViewType.text = postsModel.link
                textViewAddress.text = postsModel.address
                tvTitle.text = postsModel.title.rendered
            }
        }
    }

    class RestaurantComparator : DiffUtil.ItemCallback<PostsModel>() {
        override fun areItemsTheSame(oldItem: PostsModel, newItem: PostsModel) =
            oldItem.status == newItem.status

        override fun areContentsTheSame(oldItem: PostsModel, newItem: PostsModel) =
            oldItem == newItem
    }
}