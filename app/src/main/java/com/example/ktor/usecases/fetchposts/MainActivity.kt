package com.example.ktor.usecases.fetchposts

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ktor.databinding.ActivityMainBinding
import com.example.ktor.network.model.PostResponseItem
import com.example.ktor.utils.ApiState
import com.example.ktor.viewmodel.PostViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    private val postViewModel : PostViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        initRecyclerview()
        postViewModel.getPost()
        setContentView(binding.root)
    }

    private fun initRecyclerview() {
        binding.rvPosts.apply {
            this.layoutManager  =LinearLayoutManager(this@MainActivity)
        }

        lifecycleScope.launchWhenStarted {
            postViewModel.state.collectLatest {state->
                when(state)
                {
                    is ApiState.Loading->{
                        binding.pBar.visibility = View.VISIBLE
                    }
                    is ApiState.Success->{
                        binding.pBar.visibility = View.GONE
                        val adapter = PostsAdapter(state.data as ArrayList<PostResponseItem>)
                        binding.rvPosts.adapter = adapter
                    }
                    is ApiState.Failure->{
                        binding.pBar.visibility   = View.GONE
                        Toast.makeText(this@MainActivity, "${state.message}", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
}