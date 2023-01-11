package com.example.ktor.usecases.fetchposts

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ktor.databinding.ActivityLayoutBinding
import com.example.ktor.utils.ApiState
import com.example.ktor.viewmodel.PostViewModel

import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: PostViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val recyclerViewAdapter = RecyclerViewAdapter()

        binding.apply {
            recyclerView.apply {
                adapter = recyclerViewAdapter
                layoutManager = LinearLayoutManager(this@MainActivity)
            }

            viewModel.postsRepo.observe(this@MainActivity) { result ->
                recyclerViewAdapter.submitList(result.data)

                progressBar.isVisible = result is ApiState.Loading && result.data.isNullOrEmpty()
                textViewError.isVisible = result is ApiState.Error && result.data.isNullOrEmpty()
                textViewError.text = result.error?.localizedMessage
            }
        }
    }
}