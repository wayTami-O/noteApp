package com.example.roomsolo

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.roomsolo.screens.screens.ListScreen
import com.example.roomsolo.screens.screens.NoteDetailView
import com.example.roomsolo.screens.viewModel.NoteViewModel
import com.example.roomsolo.tools.Note
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val viewModel: NoteViewModel = hiltViewModel()

            LaunchedEffect(key1 = Unit) {
                viewModel.getNotes()
            }

            val list by viewModel.notes.collectAsState()

            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = "notes") {
                composable("notes") {
                    ListScreen( false, list) {
                        navController.navigate("note/${it?.id ?: ""}")
                    }
                }
                composable("note/{noteId}") {
                    val noteId = it.arguments?.getString("noteId")?.toIntOrNull()
//                    val note = noteId?.let { it1 -> viewModel.getById(it1) }
                    NoteDetailView(
                        noteId = noteId,
                        navController = navController,
                        newNote = noteId == null,
                        viewModel = viewModel,
                        delete = {
                            navController.navigate("notes")
                            Toast.makeText(this@MainActivity, it?.title, Toast.LENGTH_SHORT).show()
                    })
                }
            }
        }
    }
    fun getNoteById(noteId: Int, list: List<Note>): Note? {
        return list.find { it.id == noteId }
    }
}