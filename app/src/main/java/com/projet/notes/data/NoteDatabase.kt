package com.projet.notes.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.projet.notes.model.Note
import com.projet.notes.util.DateConverter
import com.projet.notes.util.UUIDConverter

@Database(entities = [Note::class], version = 1 , exportSchema = false)
@TypeConverters(DateConverter::class, UUIDConverter::class )
abstract class NoteDatabase: RoomDatabase() {
    abstract fun noteDao(): NoteDatabaseDao
}