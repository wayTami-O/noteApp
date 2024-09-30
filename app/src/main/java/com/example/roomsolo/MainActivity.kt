package com.example.roomsolo

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.roomsolo.screens.screens.ListScreen
import com.example.roomsolo.screens.screens.NoteDetailView

class MainActivity : ComponentActivity() {

    private val listNotes = listOf(
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
                    ListScreen( false, listNotes) {
                        navController.navigate("note/${it.id}")
                    }
                }
                composable("note/{noteId}") {
                    val noteId = it.arguments?.getString("noteId")?.toIntOrNull()
                    val note = noteId?.let { it1 -> getNoteById(it1) }
                    if (note != null) {
                        NoteDetailView(note = note, navController = navController, delete = {
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