package com.example.ktor.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import androidx.room.withTransaction
import com.example.ktor.network.di.WordpressApiRetro
import com.example.ktor.network.ktorService.WordpressApi
import com.example.ktor.paging.PostPaging
import com.example.ktor.utils.networkBoundResource

import kotlinx.coroutines.delay
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DaoRepository @Inject constructor(
    private val api: WordpressApi,
    private val db: PravoDatabase,
    private val wordpressApi: WordpressApiRetro
) {
    private val postsDao = db.repoDao()

    fun getPostByCategory(CategoryId: Int) = Pager(
        config = PagingConfig(
            pageSize = 10, // затова ми крашва на категории под 10 поста, ама няма да го пипам
            maxSize = 100,
            enablePlaceholders = false
        ),
        pagingSourceFactory = {
            PostPaging (
                categoryId = CategoryId,
                wordpressApi = wordpressApi
            )
        }
    ).liveData



    fun getPosts() = networkBoundResource(
        query = {
            postsDao.getAllPosts()
        },
        fetch = {
            delay(2000) // тестване
            api.getWpApi()
        },
        saveFetchResult = { giveMePosts ->
            db.withTransaction {
                postsDao.deleteAllPosts()
                postsDao.insertPosts(giveMePosts)
                // foreach
            }
        }
    )
}