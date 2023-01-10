package com.projet.notes.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.Instant
import java.util.*

@Entity(tableName = "notes_tbl")
data class Note(
    @PrimaryKey
    val id: String = UUID.randomUUID().toString(),

    @ColumnInfo(name = "note_title" )
    val title:String,

    @ColumnInfo(name = "note_des" )
    val description:String,

    @ColumnInfo(name = "note_time" )
    val time:String,

    @ColumnInfo(name = "note_rep" )
    val repetition:Int,

    @ColumnInfo(name = "note_serial" )
    val serial: Int,

    @ColumnInfo(name = "note_entry_date" )
    val entryDate: String = Date.from(Instant.now()).toString()
)
