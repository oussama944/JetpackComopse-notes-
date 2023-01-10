package com.projet.notes.components

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun NoteInputText(
    modifier: Modifier= Modifier,
    text: String,
    label: String,
    isOnlyNumber: Boolean = false,
    maxLine: Int = 1,
    onTexChange: (String) -> Unit,
    onImeAction:() ->Unit ={}
){
    val keyboardController=LocalSoftwareKeyboardController.current

    TextField(
        value = text,
        onValueChange = onTexChange,
        colors = TextFieldDefaults.textFieldColors(),
        maxLines = maxLine,
        label = {Text(text=label)},
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Done,
            keyboardType = if(isOnlyNumber){
                 KeyboardType.Number
            }else{
                KeyboardType.Text
            }
        ),
        keyboardActions = KeyboardActions(onDone = {
            onImeAction()
            keyboardController?.hide() }),
        modifier = modifier
    )


}

@Composable
fun NoteButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick:() ->Unit,
    enabled: Boolean = true
){
    Button(
        onClick = onClick,
        shape = CircleShape,
        enabled = enabled,
        modifier = modifier
    ) {
        Text(text)
    }
}