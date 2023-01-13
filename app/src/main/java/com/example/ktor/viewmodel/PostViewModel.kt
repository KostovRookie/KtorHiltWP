package com.example.ktor.viewmodel


import androidx.lifecycle.*
import com.example.ktor.data.Categories
import com.example.ktor.data.DaoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class PostViewModel @Inject constructor(repository: DaoRepository) : ViewModel() {

    val postsRepo = repository.getPosts().asLiveData()

//    private val currentCategoryPosition = MutableLiveData(0)
//
//    val posts = currentCategoryPosition.switchMap { categoryId ->
//
//        if (categories.value.isNotEmpty()) {
//            categories.value[categoryId].id?.let {
//                repository.getPostByCategory(it)
//                    .cachedIn(viewModelScope)
//            }
//
//        } else {
//            repository.getPostByCategory(categoryId).cachedIn(viewModelScope)
//
//        }
//
//    }
//    private val _categoryList = MutableStateFlow<List<Categories>>(emptyList())
//    val categories: StateFlow<List<Categories>> = _categoryList // лист по категории само
//



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

