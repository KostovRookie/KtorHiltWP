package com.example.ktor.network.model.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.ktor.network.model.PostResponseItem


@Database(entities = [PostResponseItem::class], version = 1)
abstract class RestaurantDatabase : RoomDatabase(){

    abstract fun restaurantDao(): RestaurantDao



}