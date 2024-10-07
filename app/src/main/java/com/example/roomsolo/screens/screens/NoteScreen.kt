package com.example.roomsolo.screens.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.roomsolo.screens.viewModel.NoteViewModel
import com.example.roomsolo.tools.Note
import com.example.roomsolo.ui.theme.gray
import com.example.roomsolo.ui.theme.light_gray
import kotlinx.coroutines.flow.MutableStateFlow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteDetailView(
    noteId: Int?,
    navController: NavController,
    newNote: Boolean,
    viewModel: NoteViewModel,
    delete: (Note?) -> Unit,
) {
    val note by viewModel.note.collectAsState()

    LaunchedEffect(key1 = Unit) {
        if (noteId != null) {
            viewModel.getById(noteId)
        }
    }

    var showDelete by remember {
        mutableStateOf(false)
    }

    var showDialog by remember {
        mutableStateOf(false)
    }

    var edit by remember {
        mutableStateOf(newNote)
    }

    var textTitle by remember {
        mutableStateOf(if (newNote) "" else note?.title ?: "")
    }

    var textDescription by remember {
        mutableStateOf(if (newNote) "" else note?.description ?: "")
    }

    Scaffold(
        topBar = {
            (TopAppBar(
                title = {
                    Text("Note", fontSize = 23.sp)
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Default.KeyboardArrowLeft,
                            contentDescription = "Назад",
                            tint = White
                        )
                    }
                },
                actions = {
                    if (!newNote) {
                        IconButton({ showDelete = true }) {
                            Icon(
                                Icons.Default.Delete,
                                contentDescription = "Удалить"
                            )
                        }
                    }
                    if (!edit) {
                        IconButton({ edit = true }) {
                            Icon(
                                Icons.Default.Edit,
                                contentDescription = "Редактировать"
                            )
                        }
                    } else {
                        IconButton({
                            edit = false
                            if (newNote) {
                                viewModel.insertNote(Note(null, textTitle, textDescription))
                                navController.popBackStack()
                            } else {
                                Log.d("TAG", note.toString())
                                note?.let { viewModel.updateNote(it) }
                            }
                        }) {
                            Icon(
                                Icons.Default.Check,
                                contentDescription = "Готово"
                            )
                        }
                    }
                    DropdownMenu(
                        expanded = showDelete,
                        onDismissRequest = { showDelete = false },
                        modifier = Modifier
                            .background(gray)
                    ) {
                        DropdownMenuItem(
                            text = {
                                Text(
                                    color = White,
                                    text = "Удалить"
                                )
                            },
                            onClick = {
                                showDelete = false
                                showDialog = true
                            }
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = light_gray,
                    titleContentColor = White,
                    actionIconContentColor = White
                )
            ))
        }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .background(gray)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                if (!edit) {
                    Text(
                        text = note?.title.toString(),
                        color = White,
                        modifier = Modifier
                            .fillMaxWidth()
                            .basicMarquee()
                            .padding(
                                8.dp,
                                6.dp
                            )
                            .clickable {
                                edit = true
                            }
                    )
                    Divider(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(1.dp)
                            .background(light_gray)
                    )
                    Text(
                        text = note?.description.toString(),
                        color = White,
                        modifier = Modifier
                            .padding(
                                8.dp,
                                6.dp
                            )
                            .clickable {
                                edit = true
                            }
                    )
                } else {
                    BasicTextField(
                        value = textTitle,
                        onValueChange = {
                            textTitle = it
                        },
                        modifier = Modifier
                            .background(gray)
                            .padding(
                                8.dp,
                                6.dp
                            )
                            .border(
                                0.dp,
                                Color.Transparent
                            )
                            .fillMaxWidth(),
                        textStyle = TextStyle(color = White),
                        cursorBrush = SolidColor(White),
                        decorationBox = { innerTextField ->
                            if (textTitle?.isEmpty()!!) {
                                Text(
                                    style = TextStyle(color = light_gray),
                                    text = "Введите заголовок заметки"
                                )
                            }
                            innerTextField()
                        }
                    )
                    Divider(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(1.dp)
                            .background(light_gray)
                    )
                    BasicTextField(
                        value = textDescription,
                        onValueChange = {
                            textDescription = it
                        },
                        cursorBrush = SolidColor(White),
                        modifier = Modifier
                            .background(gray)
                            .padding(
                                8.dp,
                                6.dp
                            )
                            .border(
                                0.dp,
                                Color.Transparent
                            ),
                        textStyle = TextStyle(color = White),
                        decorationBox = { innerTextField ->
                            if (textDescription?.isEmpty()!!) {
                                Text(
                                    style = TextStyle(color = light_gray),
                                    text = "Введите описание заметки"
                                )
                            }
                            innerTextField()
                        }
                    )
                }
            }
        }
    }
    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text(text = "Удалить заметку") },
            text = { Text(text = "Вы действительно хотите удалить заметку?") },
            confirmButton = {
                Button(
                    onClick = {
                        note?.let { viewModel.deleteNote(it) }
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = light_gray,
                        contentColor = White
                    )
                ) {
                    Text(
                        color = White,
                        text = "Удалить"
                    )
                }
            },
            dismissButton = {
                Button(
                    onClick = {
                        showDialog = false
                        delete(note)
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = light_gray,
                        contentColor = White
                    ),
                ) {
                    Text(
                        color = White,
                        text = "Отмена"
                    )
                }
            },
            containerColor = light_gray,
            titleContentColor = White,
            textContentColor = White,
            iconContentColor = light_gray
        )
    }
}