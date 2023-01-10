package com.example.ktor.repositories

import com.example.ktor.network.ktorService.ApiService
import com.example.ktor.network.model.PostResponseItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.util.ArrayList
import javax.inject.Inject

class PostsRepository @Inject constructor(val apiService: ApiService)  {

    fun getPost() : kotlinx.coroutines.flow.Flow<ArrayList<PostResponseItem>> = flow {
        emit(apiService.getPost())
    }.flowOn(Dispatchers.IO)

}