package com.projet.notes.data

import com.projet.notes.model.Note

class NotesDataSource{
    fun loadNotes(): List<Note>{
        return listOf(

            Note(title = "Developper coucher", description = "aaaaaa",time="1mn30", repetition = 4, serial = 15),
            Note(title = "testsdqsdqd", description = "bbbb",time="1mn50", repetition = 5, serial = 10),
            Note(title = "et pourquoi pas", description = "ccccc",time="1mn80", repetition = 6, serial = 25),
            Note(title = "et alorss", description = "ddddd",time="1mn90", repetition = 7, serial = 35),
            Note(title = "derniere note", description = "eeeeee",time="1mn1000", repetition = 8, serial = 45)

        )
    }
}