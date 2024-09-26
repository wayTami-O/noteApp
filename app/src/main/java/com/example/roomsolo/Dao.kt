package com.example.roomsolo

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface Dao {
    @Query("SELECT * FROM note")
    fun getAllNotes(): List<Note>

    @Update
    fun updateNote(note: Note)

    @Insert
    fun insertNote(note: Note)

    @Delete
    fun deleteNote(note: Note)
}