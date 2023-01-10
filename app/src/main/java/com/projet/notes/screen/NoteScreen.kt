package com.projet.notes.screen

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.projet.notes.R
import com.projet.notes.components.NoteInputText
import com.projet.notes.components.NoteButton
import com.projet.notes.data.NotesDataSource
import com.projet.notes.model.Note
import java.time.format.DateTimeFormatter

@Composable
fun NoteScreen(
    notes : List<Note>,
    onAddNote: (Note) -> Unit,
    onRemoveNote: (Note) -> Unit,
){
    var title by remember {
        mutableStateOf("")
    }
    var description by remember {
        mutableStateOf("")
    }
    var serial by remember {
        mutableStateOf("")
    }
    var repetition by remember {
        mutableStateOf("")
    }
    var time by remember {
        mutableStateOf("")
    }

    val context = LocalContext.current


    Column {
        TopAppBar(
            title = {
                    Text(text = stringResource(id = R.string.app_name))
            },
            actions = {
                Icon(imageVector = Icons.Rounded.Notifications,
                    contentDescription = "Icone de notification")
            },
            backgroundColor = Color(0xFF296697)
        )
        Column(
            modifier =Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LazyColumn{
                item{
                    NoteInputText(
                        modifier = Modifier.padding(top = 9.dp, bottom = 8.dp),
                        text = title,
                        label = "Titre",
                        onTexChange ={
                            if(it.all{ char->
                                    char.isLetter() || char.isWhitespace()
                                }) title = it
                        } )
                    NoteInputText(
                        modifier = Modifier.padding(top = 9.dp, bottom = 8.dp),
                        text = description,
                        label = "Ajouter une description",
                        onTexChange ={
                            if(it.all{ char->
                                    char.isLetter() || char.isWhitespace()
                                }) description = it
                        } )
                    NoteInputText(
                        modifier = Modifier.padding(top = 9.dp, bottom = 8.dp),
                        text = serial,
                        isOnlyNumber = true,
                        label = "Nombre de serie",
                        onTexChange ={
                            if(it.all{ char->
                                    char.isDigit()
                                }) serial = it
                        } )

                    NoteInputText(
                        modifier = Modifier.padding(top = 9.dp, bottom = 8.dp),
                        text = repetition,
                        isOnlyNumber = true,
                        label = "Nombre de repetition",
                        onTexChange ={
                            if(it.all{ char->
                                    char.isDigit()
                                }) repetition = it
                        } )

                    NoteInputText(
                        modifier = Modifier.padding(top = 9.dp, bottom = 8.dp),
                        text = time,
                        label = "Temps de repos entre les series",
                        onTexChange ={
                            if(it.all{ char->
                                    char.isDigit() || char.isLetter() || char.isWhitespace()
                                }) time = it
                        } )

                    NoteButton(
                        modifier = Modifier.padding(top = 9.dp, bottom = 8.dp),
                        text="Save",
                        enabled = title.isNotEmpty() && description.isNotEmpty(),
                        onClick = {
                            if(title.isNotEmpty() && description.isNotEmpty()){
                                if(repetition.isNotEmpty() && serial.isNotEmpty())
                                {
                                    onAddNote(Note(title = title, description = description,time = time,repetition =repetition.toInt(),serial =serial.toInt()))
                                    Toast.makeText(context,"Exercice  \"$title \"enregistrer",Toast.LENGTH_LONG).show()
                                    //Log.d("gg","$title   $description")
                                }

                                title = ""; description = "";serial = "";description = "";repetition = "";time = ""

                            }
                        })

                }

            }



            Divider(modifier= Modifier.padding(10.dp))

            LazyColumn{
                items(notes){ note ->
                    NoteRow(note=note,onNoteClicked={
                        onRemoveNote(note)
                    }) } }
        }
    }
}




@Composable
fun NoteRow(
    modifier: Modifier=Modifier,
    note: Note,
    onNoteClicked: (Note) -> Unit
){
    Surface(
        modifier
            .padding(4.dp)
            .clip(RoundedCornerShape(topEnd = 50.dp, bottomStart = 33.dp))
            .fillMaxWidth(),
        color = Color(0xFFFF5722),
        elevation = 6.dp
    ){
        Column(
        modifier = modifier
            .clickable {
                onNoteClicked(note)
            }
            .padding(horizontal = 14.dp, vertical = 6.dp),
        horizontalAlignment = Alignment.Start) {
            Text(
                text = note.title,
                style = MaterialTheme.typography.subtitle2)
            Text(
                text = note.description,
                style = MaterialTheme.typography.subtitle1)
            Text(
                text = note.time,
                style = MaterialTheme.typography.subtitle1)
            Text(
                text = note.repetition.toString(),
                style = MaterialTheme.typography.subtitle1)
            Text(
                text = note.serial.toString(),
                style = MaterialTheme.typography.subtitle1)
            Text(
                text = note.entryDate.format(DateTimeFormatter.ofPattern("EEE d MMM")),
                style = MaterialTheme.typography.caption)
        }
    }
}





@Preview
@Composable
fun NotesScreenPreview(){
    NoteScreen(notes = NotesDataSource().loadNotes(), onAddNote = {}, onRemoveNote = {})
}