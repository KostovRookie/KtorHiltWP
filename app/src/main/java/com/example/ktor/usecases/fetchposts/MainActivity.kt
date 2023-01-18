package com.example.ktor.usecases.fetchposts

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ktor.databinding.ActivityLayoutBinding
import com.example.ktor.ui.main.ViewPagerAdapter
import com.example.ktor.utils.ApiState
import com.example.ktor.viewmodel.PostViewModel
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {


    private val viewModel: PostViewModel by viewModels()
    private val testViewModel: AllViewModel by viewModels()

    //  private val postAdapter = RecyclerViewAdapter
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

// paging
//        testViewModel.posts.observe(lifecycleScope) { pagingData ->
//            postAdapter.submitData(lifecycle = lifecycle, pagingData = pagingData)


        val recyclerViewAdapter = RecyclerViewAdapter()

        val tabLayout = binding.tabLayout
        val viewPager2 = binding.viewPager2

        val adapterP = ViewPagerAdapter(supportFragmentManager, lifecycle)
        viewPager2.adapter = adapterP
        TabLayoutMediator(tabLayout, viewPager2) { tab, position ->


            when (position) {
                0 -> {
                    tab.text = "първи таб"
                }
                1 -> {
                    tab.text = "втори таб"
                }
                2 -> {
                    tab.text = "трети таб"
                }

            }

        }.attach()
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {
                Toast.makeText(this@MainActivity, "Reselected${tab?.text}", Toast.LENGTH_SHORT)
                    .show()

            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                Toast.makeText(this@MainActivity, "Unselected${tab?.text}", Toast.LENGTH_SHORT)
                    .show()
            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                Toast.makeText(this@MainActivity, "Selected${tab?.text}", Toast.LENGTH_SHORT)
                    .show()

            }
        })



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

                progressBar.isVisible =
                    result is ApiState.Loading && result.data.isNullOrEmpty()
                textViewError.isVisible =
                    result is ApiState.Error && result.data.isNullOrEmpty()
                textViewError.text = result.error?.localizedMessage
            }
        }


    }


}






