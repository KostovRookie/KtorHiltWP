package com.example.ktor.viewmodel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.ktor.data.DaoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PostViewModel @Inject constructor(repository: DaoRepository) : ViewModel() {

    val postsRepo = repository.getPosts().asLiveData()
}


//@HiltViewModel
//class PostViewModel @Inject constructor(private val postsRepository: PostsRepository) : ViewModel()  {
//
//    private val _postStateFlow : MutableStateFlow<Resource<ArrayList<PostResponseItem>>> = MutableStateFlow(Resource.Loading())
//    val state : StateFlow<Resource<ArrayList<PostResponseItem>>>   = _postStateFlow
//
//    fun getPost() = viewModelScope.launch {
//        restaurants.getPost().onStart {
//            restaurants.value = Resource.Loading()
//        }.catch {e->
//            _postStateFlow.value = Resource.Error(null,e.message)
//        }.collect {response->
//            _postStateFlow.value = Resource.Success(response)
//        }
//    }

