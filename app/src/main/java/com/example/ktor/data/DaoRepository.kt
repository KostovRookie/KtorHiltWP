package com.example.ktor.data

import androidx.room.withTransaction
import com.example.ktor.network.ktorService.WordpressApi
import com.example.ktor.utils.networkBoundResource

import kotlinx.coroutines.delay
import javax.inject.Inject

class DaoRepository @Inject constructor(
    private val api: WordpressApi,
    private val db: PravoDatabase
) {
    private val postsDao = db.repoDao()


    fun getPosts() = networkBoundResource(
        query = {
            postsDao.getAllPosts()
        },
        fetch = {
            delay(2000)
            api.getWpApi()
        },
        saveFetchResult = { restaurants ->
            db.withTransaction {
                postsDao.deleteAllPosts()
                postsDao.insertPosts(restaurants)
            }
        }
    )
}