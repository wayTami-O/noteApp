package com.example.roomsolo

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
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
import com.example.roomsolo.tools.AppDatabase
import com.example.roomsolo.tools.Note
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

//    private val viewModel by viewModels<NoteViewModel>()

//    private val listNotes = listOf(
//        Note(0, "ПоестьПоестьПоестьПоестьПоестьПоестьПоестьПоестьПоестьПоестьПоестьПоестьПоестьПоестьПоесть", "БыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстро"),
//        Note(1, "ПоестьПоестьПоестьПоестьПоестьПоестьПоестьПоестьПоестьПоестьПоестьПоестьПоестьПоестьПоесть", "БыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстро"),
//        Note(2, "ПоестьПоестьПоестьПоестьПоестьПоестьПоестьПоестьПоестьПоестьПоестьПоестьПоестьПоестьПоесть", "БыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстро"),
//        Note(3, "ПоестьПоестьПоестьПоестьПоестьПоестьПоестьПоестьПоестьПоестьПоестьПоестьПоестьПоестьПоесть", "БыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстро"),
//        Note(4, "ПоестьПоестьПоестьПоестьПоестьПоестьПоестьПоестьПоестьПоестьПоестьПоестьПоестьПоестьПоесть", "БыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстро"),
//        Note(5, "ПоестьПоестьПоестьПоестьПоестьПоестьПоестьПоестьПоестьПоестьПоестьПоестьПоестьПоестьПоесть", "БыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстро"),
//        Note(6, "ПоестьПоестьПоестьПоестьПоестьПоестьПоестьПоестьПоестьПоестьПоестьПоестьПоестьПоестьПоесть", "БыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстро"),
//        Note(7, "ПоестьПоестьПоестьПоестьПоестьПоестьПоестьПоестьПоестьПоестьПоестьПоестьПоестьПоестьПоесть", "БыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстро"),
//        Note(8, "ПоестьПоестьПоестьПоестьПоестьПоестьПоестьПоестьПоестьПоестьПоестьПоестьПоестьПоестьПоесть", "БыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстро"),
//        Note(9, "ПоестьПоестьПоестьПоестьПоестьПоестьПоестьПоестьПоестьПоестьПоестьПоестьПоестьПоестьПоесть", "БыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстроБыстро")
//    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val viewModel: NoteViewModel = hiltViewModel()

            LaunchedEffect(key1 = Unit) {
                viewModel.getNote()
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
                    val note = noteId?.let { it1 -> getNoteById(it1, list) }
                    if (note != null) {
                        NoteDetailView(note = note, navController = navController, false, viewModel, delete = {
                            navController.navigate("notes")
                            Toast.makeText(this@MainActivity, it?.title, Toast.LENGTH_SHORT).show()
                        })
                    } else {
                        NoteDetailView(note = null, navController = navController, true, viewModel, delete = {
                            navController.navigate("notes")
                            Toast.makeText(this@MainActivity, it?.title, Toast.LENGTH_SHORT).show()
                        })
                    }
                }
            }
        }
    }
    fun getNoteById(noteId: Int, list: List<Note>): Note? {
        return list.find { it.id == noteId }
    }
}