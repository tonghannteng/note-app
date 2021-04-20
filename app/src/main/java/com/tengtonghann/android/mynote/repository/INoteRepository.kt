package com.tengtonghann.android.mynote.repository

import androidx.lifecycle.LiveData
import com.tengtonghann.android.mynote.model.Note

interface INoteRepository {

    suspend fun insertNote(note: Note)

    suspend fun updateNote(note: Note)

    suspend fun deleteNote(note: Note)

    fun getAllNotes(): LiveData<List<Note>>

    fun searchNote(query: String): LiveData<List<Note>>

}