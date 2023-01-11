package com.example.ktor.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [PostsModel::class], version = 1)
abstract class PravoDatabase : RoomDatabase() {

    abstract fun repoDao(): InterfaceDao
}