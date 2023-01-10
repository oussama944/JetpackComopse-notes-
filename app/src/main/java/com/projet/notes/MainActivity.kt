package com.projet.notes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.projet.notes.data.NotesDataSource
import com.projet.notes.screen.NoteScreen
import com.projet.notes.screen.NoteViewModel
import com.projet.notes.ui.theme.NotesTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp {
                    val noteViewModel: NoteViewModel by viewModels()
                    NotesApp(noteViewModel)
            }
        }
    }
}


@Composable
fun MyApp(content: @Composable () -> Unit){
    NotesTheme {
        content()
    }
}


@Composable
fun NotesApp(noteViewModel: NoteViewModel){
    val noteList = noteViewModel.noteList.collectAsState().value

    NoteScreen(
        notes = noteList,
        onAddNote = {
            noteViewModel.addNote(it)
        },
        onRemoveNote = {
            noteViewModel.delNote(it)
        })
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    NotesTheme {
    }
}