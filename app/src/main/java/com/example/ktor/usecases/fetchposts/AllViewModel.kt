package com.example.ktor.usecases.fetchposts

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.ktor.data.Categories
import com.example.ktor.data.DaoRepository
import com.example.ktor.data.PostsModel
import com.example.ktor.network.di.WordpressApiRetro
import com.example.ktor.paging.PostPaging
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class AllViewModel @Inject constructor(private val WPRepo: DaoRepository) :
    ViewModel() {
    private val currentCategoryPosition = MutableLiveData(DEFAULT_CATEGORY_POSITION)

//    val posts: Flow<PagingData<PostsModel>> =
//        Pager(config = PagingConfig(pageSize = 20, prefetchDistance = 2),
//            pagingSourceFactory = { PostPaging()
//        ).flow.cachedIn(viewModelScope)
//            }


//    val flow = Pager(
//        // Configure how data is loaded by passing additional properties to
//        // PagingConfig, such as prefetchDistance.
//        PagingConfig(pageSize = 20, prefetchDistance = 2)
//    ) {
//        PostPaging(categoryId = 0, wordpressApi = PostsModel)
//    }.flow
//        .cachedIn(viewModelScope)


    val posts = currentCategoryPosition.switchMap { categoryId ->

        if (categories.value.isNotEmpty()) {
            categories.value[categoryId].id?.let {
                WPRepo.getPostByCategory(it)
                    .cachedIn(viewModelScope)
            }

        } else {
            WPRepo.getPostByCategory(categoryId).cachedIn(viewModelScope)

        }

    }


    private val _categoryList = MutableStateFlow<List<Categories>>(emptyList())
    val categories: StateFlow<List<Categories>> = _categoryList // лист по категории само


//    private val _postState = MutableStateFlow<PostState>(PostState.Empty)
//    val postState: StateFlow<PostState> = _postState


//    init {
//
//        viewModelScope.launch {
//
//            try {
//                _categoryList.value = WPRepo.getCategories()
//
//            } catch (_: IOException) {
//
//
//
//            } catch (_: HttpException) {
//
//            }
//
//
//        }
//    }

    fun getPostByCategory(categoryPosition: Int) {
        currentCategoryPosition.value = categoryPosition
    }


    companion object {
        private const val DEFAULT_CATEGORY_POSITION = 4
    }
}





