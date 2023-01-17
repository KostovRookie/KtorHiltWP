package com.example.ktor.utils

import androidx.room.TypeConverter
import com.example.ktor.data.Embedd
import com.example.ktor.data.Title
import com.example.ktor.data.WpFeaturedmedia
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

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

    fun fromList(value: List<WpFeaturedmedia>) = Gson().toJson(value)

    @TypeConverter
    fun toList(value: String): List<WpFeaturedmedia> {
        val listType: Type = object : TypeToken<List<WpFeaturedmedia>>() {}.type
        return Gson().fromJson(value, listType)
    }


//    @TypeConverter
//    fun fromList(value : List<String>) = Json.encodeToString(value)
//
//    @TypeConverter
//    fun toList(value: String) = Json.decodeFromString<List<String>>(value)

    //_embedded: Embedd   >> wp_FeaturedMedia

//    @TypeConverter
//    fun fromEmbedd(_embedded: Embedd): List<WpFeaturedmedia> {
//        return _embedded.wp_FeaturedMedia
//    }
//
//    @TypeConverter
//    fun toEmbedd(wp_FeaturedMedia: String): Embedd {
//        return Embedd(List<WpFeaturedmedia>)
//    }


//    @TypeConverter
//    fun fromList(wpFeaturedmedia: List<WpFeaturedmedia>): String {
//        return wpFeaturedmedia.joinToString(",")
//    }
//
//    @TypeConverter
//    fun toList(data: String): List<String> {
//        return data.split(",")
//    }


    //gson test conversion
//    @TypeConverter
//    fun fromStringList(_embedded: List<Embedd>): String {
//
//        return Gson().toJson(_embedded)
//    }
//
//    @TypeConverter
//    fun toStringList(_embedded: List<Embedd>): List<Embedd> {
//        return try {
//            Gson().fromJson<List<String>>(_embedded) //using extension function
//        } catch (e: Exception) {
//            listOf()
//        }
//    }
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
//    fun fromList(_embedded : List<WpFeaturedmedia>) = Json.encodeToString(_embedded)
//
//    @TypeConverter
//    fun toList(_embedded: List<WpFeaturedmedia>) = Json.decodeFromString<List<WpFeaturedmedia>>(_embedded)
//}

//@TypeConverter
//fun fromEmbedd(_embedded: Embedd): String {
//    return _embedded.wp_FeaturedMedia
//}
//
//@TypeConverter
//fun toEmbedd(wp_FeaturedMedia: String): Embedd {
//    return Embedd(wp_FeaturedMedia)
//}