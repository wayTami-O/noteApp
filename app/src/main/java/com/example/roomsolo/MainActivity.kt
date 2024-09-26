package com.example.roomsolo

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.roomsolo.ui.theme.gray
import com.example.roomsolo.ui.theme.light_gray

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = "notes") {
                composable("notes") {
                    Header(this@MainActivity)
                }
                composable("note") {

                }
                composable("editNote") {

                }
                composable("changeNote") {

                }
            }
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Header(context: Context) {
    val listNotes = listOf(
        Note(0, "Поесть", "Быстро"),
        Note(1, "Поесть", "Быстро"),
        Note(2, "Поесть", "Быстро"),
        Note(3, "Поесть", "Быстро"),
        Note(4, "Поесть", "Быстро"),
        Note(5, "Поесть", "Быстро"),
        Note(6, "Поесть", "Быстро"),
    )

    Scaffold(
        topBar = {
            @OptIn(ExperimentalMaterial3Api::class)
            TopAppBar(title= { Text("Note", fontSize = 23.sp)},
                actions={
                    IconButton({ }) { Icon(Icons.Default.Delete, contentDescription = "Удалить")}
                    IconButton({ }) {Icon(Icons.Default.Edit, contentDescription = "Редактировать")}
                },
                colors= TopAppBarDefaults.topAppBarColors(
                    containerColor = light_gray,
                    titleContentColor = White,
                    actionIconContentColor = White))
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                Toast.makeText(context, "Add", Toast.LENGTH_SHORT).show()
            }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add")
            }
        }
    ){
        Box(
            modifier = Modifier
                .padding(it)
                .background(gray),
            contentAlignment =  Alignment.Center
        ) {
            LazyVerticalStaggeredGrid(
                columns = StaggeredGridCells.Fixed(2),
                modifier = Modifier
                    .fillMaxSize(),
                contentPadding = PaddingValues(20.dp, 15.dp),
                verticalItemSpacing = 15.dp
            ) {
                items(listNotes) {note ->
                    Text(text = note.description)
                }
            }
        }
    }
}

@Composable
fun Card() {

}