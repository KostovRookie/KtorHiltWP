package com.example.ktor.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ktor.R
import com.example.ktor.databinding.ActivityLayoutBinding
import com.example.ktor.databinding.FragmentThirdBinding
import com.example.ktor.usecases.fetchposts.RecyclerViewAdapter
import com.example.ktor.utils.ApiState


class ThirdFragment : Fragment(R.layout.fragment_third) {
    // Scoped to the lifecycle of the fragment's view (between onCreateView and onDestroyView)
    private var fragmentThirdBinding: FragmentThirdBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentThirdBinding.bind(view)
        fragmentThirdBinding = binding

        binding.textViewFragment.text = "Hi how are yu, this is third fragment"
    }

    override fun onDestroyView() {
        // Consider not storing the binding instance in a field, if not needed.
        fragmentThirdBinding = null
        super.onDestroyView()
    }


//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_third, container, false)
//
//
//
//
//
//
//    }








}

