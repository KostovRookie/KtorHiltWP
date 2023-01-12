package com.example.ktor.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.ktor.utils.SourceTypeConvertor

@Database(
    entities = [PostsModel::class],
    version = 1,
    exportSchema = false)
@TypeConverters(SourceTypeConvertor::class)
abstract class PravoDatabase : RoomDatabase() {

    abstract fun repoDao(): InterfaceDao
}