package com.projet.notes.util

import androidx.room.TypeConverter
import java.util.*

class UUIDConverter {
    @TypeConverter
    fun fromUUID(uuid: UUID): String?{
        return uuid.toString()
    }
    @TypeConverter
    fun uuidFromSting(string: String?): UUID?{
        return UUID.fromString(string)
    }
}