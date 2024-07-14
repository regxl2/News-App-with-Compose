package com.example.news.data.model

import androidx.room.TypeConverter
import javax.inject.Inject

class Converters@Inject constructor(){
    @TypeConverter
    fun fromSourceToString(source: Source): String{
        return "${source.id},${source.name}"
    }

    @TypeConverter
    fun fromStringToSource(str: String): Source{
        return str.split(',').let {
            Source(id = it[0], name = it[1])
        }
    }
}