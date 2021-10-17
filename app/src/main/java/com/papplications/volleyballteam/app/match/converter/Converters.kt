package com.papplications.volleyballteam.app.match.converter

import androidx.room.TypeConverter
import java.sql.Date
import java.text.DateFormat

class Converters {
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time?.toLong()
    }
}