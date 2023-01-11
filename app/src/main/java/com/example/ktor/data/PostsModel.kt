package com.example.ktor.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable


@Serializable
data class Title (
    val rendered: String
    )


@Serializable
@Entity(tableName = "stupid")
data class PostsModel(
    @PrimaryKey val id: Int?=0,
    val status: String?=null,
    val link: String?=null,
    val slug: String?=null,
    val address: String?=null,
    val title:Title
)

