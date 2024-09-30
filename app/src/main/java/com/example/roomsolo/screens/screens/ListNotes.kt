package com.example.roomsolo.screens.screens

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
import androidx.compose.material3.Divider
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.roomsolo.Note
import com.example.roomsolo.ui.theme.dark_gray
import com.example.roomsolo.ui.theme.gray
import com.example.roomsolo.ui.theme.light_gray

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListScreen(
    showActions: Boolean,
    listNotes: List<Note>,
    onClick: (Note) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Note", fontSize = 23.sp) },
                actions = {
                    if (showActions) {
                        IconButton({ }) {
                            Icon(
                                Icons.Default.Delete,
                                contentDescription = "Удалить"
                            )
                        }
                        IconButton({

                        }) { Icon(Icons.Default.Edit, contentDescription = "Редактировать") }
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = light_gray,
                    titleContentColor = White,
                    actionIconContentColor = White
                )
            )
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
fun ListNotesView(listNotes: List<Note>, paddingScafold: PaddingValues, onClick: (Note) -> Unit) {
    Box(
        modifier = Modifier
            .padding(paddingScafold)
            .background(gray),
        contentAlignment = Alignment.Center
    ) {
        LazyVerticalStaggeredGrid(
            columns = StaggeredGridCells.Fixed(2),
            modifier = Modifier
                .fillMaxSize(),
            contentPadding = PaddingValues(20.dp, 15.dp),
            verticalItemSpacing = 15.dp,
            horizontalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            items(listNotes) { note ->
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
            color = Color.White
        )
    }
}