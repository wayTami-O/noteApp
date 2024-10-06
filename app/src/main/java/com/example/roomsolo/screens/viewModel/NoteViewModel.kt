package com.example.roomsolo.screens.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.roomsolo.tools.AppDatabase
import com.example.roomsolo.tools.Note
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(
    private val dataBase: AppDatabase
) : ViewModel() {
    private val _notes = MutableStateFlow(emptyList<Note>())
    val notes: StateFlow<List<Note>> = _notes.asStateFlow()
   fun getNote() {
        viewModelScope.launch(Dispatchers.IO) {
            _notes.emit(dataBase.noteDao().getAllNotes())
        }
   }

    fun insertNote(note: Note) {
        viewModelScope.launch(Dispatchers.IO) {
            dataBase.noteDao().insertNote(note)
            getNote()
        }
    }
}