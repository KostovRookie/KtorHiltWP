package com.example.ktor.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ktor.network.model.PostResponseItem
import com.example.ktor.repositories.PostsRepository
import com.example.ktor.utils.ApiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostViewModel @Inject constructor(private val postsRepository: PostsRepository) : ViewModel()  {

    private val _postStateFlow : MutableStateFlow<ApiState<ArrayList<PostResponseItem>>> = MutableStateFlow(ApiState.Loading())
    val state : StateFlow<ApiState<ArrayList<PostResponseItem>>>   = _postStateFlow

    fun getPost() = viewModelScope.launch {
        postsRepository.getPost().onStart {
            _postStateFlow.value = ApiState.Loading()
        }.catch {e->
            _postStateFlow.value = ApiState.Failure(null,e.message)
        }.collect {response->
            _postStateFlow.value = ApiState.Success(response)
        }
    }

}