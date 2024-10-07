package com.example.roomsolo.tools

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Note::class], version = 2)
abstract class AppDatabase : RoomDatabase() {
    abstract fun noteDao(): Dao
}
