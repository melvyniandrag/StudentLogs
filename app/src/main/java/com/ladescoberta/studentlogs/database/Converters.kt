package com.ladescoberta.studentlogs.database

import androidx.room.TypeConverter
import java.util.Date

class Converters {
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time?.toLong()
    }

    @TypeConverter
    fun childFromString(childString: String?): Child? {
        return childString?.let {
            val childValues = childString.split(";")
            Child(childValues[0].toInt(), childValues[1], childValues[2])
        }
    }

    @TypeConverter
    fun childToString(child: Child?): String? {
        return child?.let{
            child.childID.toString() + ";" + child.firstName + ";" + child.lastName
        }
    }


}
