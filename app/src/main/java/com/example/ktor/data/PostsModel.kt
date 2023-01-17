package com.example.ktor.data

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
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
    val _embedded: Embedd? = null  // проблем
)


@Entity
@Serializable
data class WpFeaturedmedia( //файнъл проблем
    val source_url: String?=null
)



@Serializable
data class Title(
    val rendered: String
)

@Serializable
data class Embedd(
  //  @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "wp_media")
    var wp_FeaturedMedia: List<WpFeaturedmedia>

)

//@Serializable
//data class Categories(
//    val count: Int? = 0,
//    val id: Int? = 0,
//    val name: String? = null,
//    val slug: String? = null
//)

