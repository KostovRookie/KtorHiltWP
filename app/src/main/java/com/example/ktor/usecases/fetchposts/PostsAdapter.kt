package com.example.ktor.usecases.fetchposts

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ktor.databinding.ItemViewPostBinding
import com.example.ktor.network.model.PostResponseItem

class PostsAdapter(private val response: ArrayList<PostResponseItem>) :
    RecyclerView.Adapter<PostViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        return PostViewHolder(
            ItemViewPostBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.bindData(response, position)
    }

    override fun getItemCount(): Int {
        if (response.isNotEmpty()) {
            return response.size
        }
        return 0
    }
}

class PostViewHolder(private val binding: ItemViewPostBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bindData(response: java.util.ArrayList<PostResponseItem>, position: Int) {
     //   binding.
         binding.rvTitle.text = response[position].title?.rendered.toString()
       //   binding.rvBody.text = response[position].body
     //   binding.rvTitle.text = response[position].title
    }

}
