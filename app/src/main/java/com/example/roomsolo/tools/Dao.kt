package com.example.roomsolo.tools

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface Dao {
    @Query("SELECT * FROM note")
    fun getAllNotes(): List<Note>

    @Query("SELECT * FROM note WHERE id = :id")
    fun getById(id: Int): Note

    @Update
    fun updateNote(note: Note)

    @Insert
    fun insertNote(note: Note)

    @Delete
    fun deleteNote(note: Note)
}