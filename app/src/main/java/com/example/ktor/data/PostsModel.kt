package com.example.ktor.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable


@Serializable
@Entity(tableName = "stupid")
data class PostsModel(
    @PrimaryKey val id: Int,
    val status: String,
    val link: String,
    val slug: String,
    val address: String
)