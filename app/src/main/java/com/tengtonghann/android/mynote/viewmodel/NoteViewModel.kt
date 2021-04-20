package com.tengtonghann.android.mynote.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tengtonghann.android.mynote.model.Note
import com.tengtonghann.android.mynote.repository.INoteRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
class NoteViewModel @ViewModelInject constructor(
    private val noteRepository: INoteRepository
) : ViewModel() {

    fun getAllNotes() {
        viewModelScope.launch {
            noteRepository.getAllNotes()
        }
    }

    fun insertNote(note: Note) {
        viewModelScope.launch {
            noteRepository.insertNote(note)
        }
    }
}