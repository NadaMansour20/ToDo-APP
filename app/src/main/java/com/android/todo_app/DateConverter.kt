package com.android.todo_app

import androidx.room.TypeConverter
import java.util.Date

class DateConverter {
    @TypeConverter
    fun fromdate(date:Date):Long{
        return date.time
    }
    @TypeConverter
    fun todate(time:Long):Date{
        return Date(time)
    }
}