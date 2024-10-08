package com.example.roomsolo

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Note(
    @PrimaryKey
    val id: Long,
    @ColumnInfo
    val title: String,
    @ColumnInfo
    val description: String
)