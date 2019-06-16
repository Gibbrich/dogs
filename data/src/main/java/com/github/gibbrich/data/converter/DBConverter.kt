package com.github.gibbrich.data.converter

import androidx.room.TypeConverter
import java.util.*

class DBConverter {
    @TypeConverter
    fun fromString(value: String?): UUID? = value?.let { UUID.fromString(it) }

    @TypeConverter
    fun toString(value: UUID?): String? = value?.toString()
}