package com.example.ktor.utils

import androidx.room.TypeConverter
import com.example.ktor.data.Title
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


//    @TypeConverter
//    fun fromEmbed(_embedded: WpFeaturedmedia): String {
//        return _embedded.source_url
//    }
//
//    @TypeConverter
//    fun toEmbed(source_url: String): WpFeaturedmedia {
//        return WpFeaturedmedia(source_url)
//    }


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