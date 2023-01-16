package com.example.ktor.data

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
@Entity(tableName = "stupid")
data class PostsModel(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val status: String? = null,
    val link: String? = null,
    val slug: String? = null,
    val address: String? = null,
    val title: Title,
    
       @Embedded
     val _embedded: Embedd  // проблем
)

@Serializable
data class Title(
    val rendered: String
)

@Serializable
data class Embedd( // проблем2
    @Embedded
    @SerialName("wp:featuredmedia")
    val wp_FeaturedMedia: List<WpFeaturedmedia>
)
//{
//    constructor() : this("")
//}

@Serializable
data class WpFeaturedmedia( //файнъл проблем
    val source_url: String
)

@Serializable
data class Categories(
    val count: Int? = 0,
    val id: Int? = 0,
    val name: String? = null,
    val slug: String? = null
)

