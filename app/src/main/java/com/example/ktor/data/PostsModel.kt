package com.example.ktor.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable





@Serializable
@Entity(tableName = "stupid")
data class PostsModel(
    @PrimaryKey (autoGenerate = true) val id: Int,
    val status: String?=null,
    val link: String?=null,
    val slug: String?=null,
    val address: String?=null,
    val title:Title,
  //  val _embedded: Embedded
)
@Serializable
data class Title (
    val rendered: String
)

@Serializable
data class Embedded(
    @SerialName("wp:featuredmedia")
    val wp_FeaturedMedia: List<WpFeaturedmedia>
)
@Serializable
data class WpFeaturedmedia(
    val source_url: String
)

