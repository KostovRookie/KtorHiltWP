package com.example.ktor.utils

import androidx.room.TypeConverter
import com.example.ktor.data.Embedd
import com.example.ktor.data.Title
import com.example.ktor.data.WpFeaturedmedia
import kotlinx.serialization.json.Json

class SourceTypeConvertor {
    @TypeConverter
    fun fromTitle(title: Title): String {
        return title.rendered
    }

    @TypeConverter //converter for title String
    fun toTitle(rendered: String): Title {
        return Title(rendered)
    }

    @TypeConverter
    fun fromEmbedd(_embedded: Embedd): List<WpFeaturedmedia> {
        return _embedded.wp_FeaturedMedia
    }

    @TypeConverter
    fun toEmbedd(wp_FeaturedMedia: String): WpFeaturedmedia {
        return WpFeaturedmedia(wp_FeaturedMedia)
    }


    @TypeConverter
    fun fromList(wpFeaturedmedia: List<WpFeaturedmedia>): String {
        return wpFeaturedmedia.joinToString(",")
    }

    @TypeConverter
    fun toList(data: String): List<String> {
        return data.split(",")
    }


//        @TypeConverter
//        fun toListOfStrings(_embedded: String): List<String> {
//            return _embedded.split(",")
//        }
//        @TypeConverter
//        fun fromListOfStrings(listOfString: List<String>): String {
//            return listOfString.joinToString(",")
//        }
//    @TypeConverter
//    fun fromListOldToString(_embedded: List<WpFeaturedmedia>): String = _embedded.toString()
//    @TypeConverter
//    fun toListFromString(source_url: String): List<WpFeaturedmedia> {
//        val result = ArrayList<WpFeaturedmedia>()
//        val split =source_url.replace("[","").replace("]","").replace(" ","").split(",")
//        for (n in split) {
//            try {
//                result.joinToString(",")
//            } catch (_: Exception) {
//
//            }
//        }
//        return result
//    }

//    @TypeConverter
//    fun fromList(value : List<String>) = Json.encodeToString(value)
//
//    @TypeConverter
//    fun toList(value: String) = Json.decodeFromString<List<String>>(value)
}