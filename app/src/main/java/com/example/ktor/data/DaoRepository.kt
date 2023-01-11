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
    private val restaurantDao = db.repoDao()

    fun getPosts() = networkBoundResource(
        query = {
            restaurantDao.getAllPosts()
        },
        fetch = {
            delay(2000)
            api.getWpApi()
        },
        saveFetchResult = { restaurants ->
            db.withTransaction {
                restaurantDao.deleteAllPosts()
                restaurantDao.insertPosts(restaurants)
            }
        }
    )
}