package com.example.ktor.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface InterfaceDao {

    @Query("SELECT * FROM stupid")
    fun getAllPosts(): Flow<List<PostsModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPosts(postsModels: List<PostsModel>)

    @Query("DELETE FROM stupid")
    suspend fun deleteAllPosts()
}