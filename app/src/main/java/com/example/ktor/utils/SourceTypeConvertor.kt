package com.example.ktor.utils

import androidx.room.TypeConverter
import com.example.ktor.data.Title

class SourceTypeConvertor {
    @TypeConverter
    fun fromTitle(title: Title): String  {
    return title.rendered
    }

    @TypeConverter
    fun toTitle(rendered: String): Title {
        return Title(rendered)
    }
}