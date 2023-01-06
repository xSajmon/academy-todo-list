package com.simon.academytodolist.source

import androidx.room.TypeConverter
import java.time.ZonedDateTime

class Converters {

    @TypeConverter
    fun toDateString(date: ZonedDateTime): String {
        return date.toString()
    }
    @TypeConverter
    fun stringToDate(dateString: String?): ZonedDateTime? {
        return ZonedDateTime.parse(dateString)
    }

}