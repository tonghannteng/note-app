package com.tengtonghann.android.mynote.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tengtonghann.android.mynote.model.Note
import com.tengtonghann.android.mynote.repository.INoteRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
class NoteViewModel @ViewModelInject constructor(
    private val noteRepository: INoteRepository
) : ViewModel() {

    private val _allNotes = MutableLiveData<List<Note>>()
    private val _searchNote = MutableLiveData<List<Note>>()

    val allNotes: LiveData<List<Note>>
        get() = _allNotes

    val searchNoteResult: LiveData<List<Note>>
        get() = _searchNote

    fun getAllNotes() {
        viewModelScope.launch {
            noteRepository.getAllNotes().collect {
                _allNotes.value = it
            }
        }
    }

    fun insertNote(note: Note) {
        viewModelScope.launch {
            noteRepository.insertNote(note)
        }
    }

    fun searchNote(query: String) {
        viewModelScope.launch {
            noteRepository.searchNote(query).collect {
                _searchNote.value = it
            }
        }
    }

    fun deleteNote(note: Note) {
        viewModelScope.launch {
            noteRepository.deleteNote(note)
        }
    }

    fun updateNote(note: Note) {
        viewModelScope.launch {
            noteRepository.updateNote(note)
        }
    }
}