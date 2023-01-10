package com.projet.notes.repository


import com.projet.notes.data.NoteDatabaseDao
import com.projet.notes.model.Note
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class NoteRepository @Inject constructor(
    private val noteDatabaseDao: NoteDatabaseDao){
    suspend fun addNote(note: Note) = noteDatabaseDao.insert(note)
    suspend fun updNote(note: Note) = noteDatabaseDao.update(note)
    suspend fun delNote(note: Note) = noteDatabaseDao.deleteNote(note)
    suspend fun deleteAllNotes() = noteDatabaseDao.deleteAll()
    suspend fun getAllNotes():Flow<List<Note>> = noteDatabaseDao.getNotes().flowOn(Dispatchers.IO)
        .conflate()
}