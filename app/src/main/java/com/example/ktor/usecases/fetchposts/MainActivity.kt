package com.example.ktor.usecases.fetchposts

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.example.ktor.databinding.ActivityLayoutBinding
import com.example.ktor.utils.ApiState
import com.example.ktor.viewmodel.PostViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout

import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {


    private val viewModel: PostViewModel by viewModels()
    private lateinit var scrollListener: RecyclerView.OnScrollListener
    private val lastVisibleItemPosition: Int = 1
    private var currentPage = 1
    private var totalAvailablePages = 1
    private var page = 0
    private var totalResults = 10


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val recyclerViewAdapter = RecyclerViewAdapter()




        binding.apply {
            recyclerView.apply {
                adapter = recyclerViewAdapter
                layoutManager = LinearLayoutManager(this@MainActivity)
                addOnScrollListener(object :
                    RecyclerView.OnScrollListener() {    // scroll listener test
                    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                        super.onScrolled(recyclerView, dx, dy)
                        if (!binding.recyclerView.canScrollVertically(1)) {
                            if (page <= totalResults) {
                                page += 1
                            }
                        }
                    }
                })
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





