package com.example.roomsolo.tools

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Note(
    @PrimaryKey(autoGenerate = true)
    val id: Int?,
    @ColumnInfo
    val title: String,
    @ColumnInfo
    val description: String
)