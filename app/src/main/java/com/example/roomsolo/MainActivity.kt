package com.example.roomsolo

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.room.Delete
import com.example.roomsolo.ui.theme.dark_gray
import com.example.roomsolo.ui.theme.gray
import com.example.roomsolo.ui.theme.light_gray

class MainActivity : ComponentActivity() {

    val listNotes = listOf(
        Note(0, "ПоестьПоестьПоестьПоестьПоестьПоестьПоестьПоестьПоестьПоестьПоестьПоестьПоестьПоестьПоесть", "БыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстро"),
        Note(1, "ПоестьПоестьПоестьПоестьПоестьПоестьПоестьПоестьПоестьПоестьПоестьПоестьПоестьПоестьПоесть", "БыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстро"),
        Note(2, "ПоестьПоестьПоестьПоестьПоестьПоестьПоестьПоестьПоестьПоестьПоестьПоестьПоестьПоестьПоесть", "БыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстро"),
        Note(3, "ПоестьПоестьПоестьПоестьПоестьПоестьПоестьПоестьПоестьПоестьПоестьПоестьПоестьПоестьПоесть", "БыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстро"),
        Note(4, "ПоестьПоестьПоестьПоестьПоестьПоестьПоестьПоестьПоестьПоестьПоестьПоестьПоестьПоестьПоесть", "БыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстро"),
        Note(5, "ПоестьПоестьПоестьПоестьПоестьПоестьПоестьПоестьПоестьПоестьПоестьПоестьПоестьПоестьПоесть", "БыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстро"),
        Note(6, "ПоестьПоестьПоестьПоестьПоестьПоестьПоестьПоестьПоестьПоестьПоестьПоестьПоестьПоестьПоесть", "БыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстро"),
        Note(7, "ПоестьПоестьПоестьПоестьПоестьПоестьПоестьПоестьПоестьПоестьПоестьПоестьПоестьПоестьПоесть", "БыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстро"),
        Note(8, "ПоестьПоестьПоестьПоестьПоестьПоестьПоестьПоестьПоестьПоестьПоестьПоестьПоестьПоестьПоесть", "БыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстро"),
        Note(9, "ПоестьПоестьПоестьПоестьПоестьПоестьПоестьПоестьПоестьПоестьПоестьПоестьПоестьПоестьПоесть", "БыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстро")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = "notes") {
                composable("notes") {
                    Header( false, listNotes) {
                        navController.navigate("note/${it.id}")
                    }
                }
                composable("note/{noteId}") {
                    val noteId = it.arguments?.getString("noteId")?.toIntOrNull()
                    val note = noteId?.let { it1 -> getNoteById(it1) }
                    if (note != null) {
                        NoteDetailView(note = note, navController =  navController, delete = {
                            navController.navigate("notes")
                            Toast.makeText(this@MainActivity, it.title, Toast.LENGTH_SHORT).show()
                        },
                        editNote = {
                            navController.navigate("editNote")
                            Toast.makeText(this@MainActivity, it.title, Toast.LENGTH_SHORT).show()
                        })
                    }
                }
                composable("editNote") {

                }
                composable("changeNote") {

                }
            }
        }
    }
    private fun getNoteById(noteId: Int): Note? {
        return listNotes.find { it.id == noteId }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Header(showActions: Boolean, listNotes: List<Note>,  onClick: (Note) -> Unit) {
    Scaffold(
        topBar = {
            @OptIn(ExperimentalMaterial3Api::class)
            TopAppBar(title= { Text("Note", fontSize = 23.sp)},
                actions= {if (showActions) {
                    IconButton({ }) { Icon(Icons.Default.Delete, contentDescription = "Удалить")}
                    IconButton({

                    }) {Icon(Icons.Default.Edit, contentDescription = "Редактировать")}
                }},
                colors= TopAppBarDefaults.topAppBarColors(
                    containerColor = light_gray,
                    titleContentColor = White,
                    actionIconContentColor = White))
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {

            }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add")
            }
        }
    ) {
        ListNotesView(listNotes = listNotes, paddingScafold = it, onClick)
    }
}

@Composable
fun ListNotesView(listNotes: List<Note>, paddingScafold: PaddingValues, onClick:(Note) -> Unit) {
    Box(
        modifier = Modifier
            .padding(paddingScafold)
            .background(gray),
        contentAlignment =  Alignment.Center
    ) {
        LazyVerticalStaggeredGrid(
            columns = StaggeredGridCells.Fixed(2),
            modifier = Modifier
                .fillMaxSize(),
            contentPadding = PaddingValues(20.dp, 15.dp),
            verticalItemSpacing = 15.dp,
            horizontalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            items(listNotes) {note ->
                Card(note) {
                    onClick(it)
                }
            }
        }
    }
}

@Composable
fun Card(note: Note, onClick: (Note) -> Unit) {
    Column(
        modifier = Modifier
            .wrapContentSize()
            .background(
                dark_gray,
                RoundedCornerShape(28.dp)
            )
            .clickable {
                onClick(note)
            },
        verticalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    16.dp,
                    12.dp,
                    12.dp,
                    0.dp
                )
                .basicMarquee(),
            color = White,
            text = note.title
        )
        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(gray)
        )
        Text(
            text = note.description,
            modifier = Modifier
                .padding(
                    12.dp,
                    0.dp,
                    12.dp,
                    16.dp
                ),
            maxLines = 5,
            overflow = TextOverflow.Ellipsis,
            color = White
        )
    }
}

@Composable
fun NoteDetailView(note: Note, navController: NavController, delete: (Note) -> Unit, editNote: (Note) -> Unit) {
    var showDelete by remember {
        mutableStateOf(false)
    }
    
    var showDialog by remember {
        mutableStateOf(false)
    }

    Scaffold(
        topBar = {
            @OptIn(ExperimentalMaterial3Api::class)
            TopAppBar(title= { Text("Note", fontSize = 23.sp)},
                navigationIcon = {
                    IconButton(onClick = { navController.navigate("notes") }) {
                        Icon(imageVector = Icons.Default.KeyboardArrowLeft, contentDescription = "Назад", tint = White)
                    }
                },
                actions= {
                    IconButton({ showDelete = true }) { Icon(Icons.Default.Delete, contentDescription = "Удалить")}
                    IconButton({ editNote(note) }) { Icon(Icons.Default.Edit, contentDescription = "Редактировать") }
                    DropdownMenu(
                        expanded = showDelete,
                        onDismissRequest = { showDelete = false },
                        modifier = Modifier
                            .background(gray)
                    ) {
                        DropdownMenuItem(
                            text = { Text(
                                    color = White,
                                    text = "Удалить"
                            ) },
                            onClick = {
                                // Действие при нажатии на кнопку "Удалить все"
//                                Toast.makeText(context, "Все заметки удалены", Toast.LENGTH_SHORT).show()
                                showDelete = false
                                showDialog = true
                            }
                        )
                    }
                },
                    colors= TopAppBarDefaults.topAppBarColors(
                    containerColor = light_gray,
                    titleContentColor = White,
                    actionIconContentColor = White))
        }
    ){
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
                Text(
                    text = note.title,
                    color = White,
                    modifier = Modifier
                        .fillMaxWidth()
                        .basicMarquee()
                )
                Divider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(1.dp)
                        .background(light_gray)
                )
                Text(
                    text = note.description,
                    color = White
                )
            }
        }
    }
    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text(text = "Удалить заметку") },
            text = { Text(text = "Вы действительно хотите удалить заметку?") },
            confirmButton = {
                Button(onClick = { showDialog = false },
                    colors = ButtonDefaults.buttonColors(containerColor = light_gray, contentColor = White)
                ) {
                    Text(
                        color = White,
                        text = "Удалить"
                    )
                }
            },
            dismissButton = {
                Button(onClick = {
                    showDialog = false
                    delete(note)
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = light_gray, contentColor = White),
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