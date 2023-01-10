package com.example.ktor.network.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Entity(tableName = "rest")
@Serializable
data class PostResponseItem(
   // val body: String,
//    val userId: Int?=0,
//    val _embedded: Embedded,
//    val content: Content,
//    val date: String?=null,
//    val id: Int?=0,
    @PrimaryKey val title: Title?=null
)