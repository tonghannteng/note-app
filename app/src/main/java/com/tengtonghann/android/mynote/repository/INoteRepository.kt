package com.tengtonghann.android.mynote.repository

import com.tengtonghann.android.mynote.model.Note
import kotlinx.coroutines.flow.Flow

interface INoteRepository {

    suspend fun insertNote(note: Note)

    suspend fun updateNote(note: Note)

    suspend fun deleteNote(note: Note)

    suspend fun getAllNotes(): Flow<List<Note>>

    suspend fun searchNote(query: String): Flow<List<Note>>

}