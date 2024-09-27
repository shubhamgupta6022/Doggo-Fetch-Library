package com.sgupta.doggofetch.data.converters

import androidx.room.TypeConverter

internal class Converters {
    @TypeConverter
    fun fromString(value: String?): List<String>? {
        return value?.split(",")
    }

    @TypeConverter
    fun fromList(list: List<String>?): String? {
        return list?.joinToString(",")
    }
}