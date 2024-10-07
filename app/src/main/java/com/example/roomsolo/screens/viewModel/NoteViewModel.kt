package com.example.roomsolo.screens.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.roomsolo.tools.AppDatabase
import com.example.roomsolo.tools.Dao
import com.example.roomsolo.tools.Note
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(
    private val dao: Dao
) : ViewModel() {
    private val _notes = MutableStateFlow(emptyList<Note>())
    val notes: StateFlow<List<Note>> = _notes.asStateFlow()

    private val _note = MutableStateFlow<Note?>(null)
    val note: StateFlow<Note?> = _note.asStateFlow()
   fun getNotes() {
        viewModelScope.launch(Dispatchers.IO) {
            _notes.emit(dao.getAllNotes())
        }
   }

    fun insertNote(note: Note) {
        viewModelScope.launch(Dispatchers.IO) {
            dao.insertNote(note)
            getNotes()
        }
    }

    fun updateNote(note: Note) {
        viewModelScope.launch(Dispatchers.IO) {
            dao.updateNote(note)
            getNotes()
        }
    }

    fun deleteNote(note: Note) {
        viewModelScope.launch(Dispatchers.IO) {
            dao.deleteNote(note)
            getNotes()
        }
    }

    fun getById(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            _note.emit(dao.getById(id))
        }
    }
}