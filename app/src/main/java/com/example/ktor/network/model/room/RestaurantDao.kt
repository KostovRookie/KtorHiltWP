package com.example.ktor.network.model.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.ktor.network.model.PostResponseItem
import kotlinx.coroutines.flow.Flow


@Dao
interface RestaurantDao {


    @Query("SELECT * FROM rest")
    fun getAllData(): Flow<List<PostResponseItem>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertData(restaurant: List<PostResponseItem>)

@Query("DELETE FROM rest")
    suspend fun deleteAllData()


}