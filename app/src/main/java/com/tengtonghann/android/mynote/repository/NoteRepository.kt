package com.tengtonghann.android.mynote.repository

import com.tengtonghann.android.mynote.db.NoteDao
import com.tengtonghann.android.mynote.model.Note
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@ExperimentalCoroutinesApi
@Singleton
class NoteRepository @Inject constructor(
    private val noteDao: NoteDao
) : INoteRepository {

    override suspend fun insertNote(note: Note) {
        noteDao.insertNote(note)
    }

    override suspend fun updateNote(note: Note) {
        noteDao.updateNote(note)
    }

    override suspend fun deleteNote(note: Note) {
        noteDao.deleteNote(note)
    }

    override suspend fun getAllNotes(): Flow<List<Note>> {
        return noteDao.getAllNotes()
    }

    override suspend fun searchNote(query: String): Flow<List<Note>> {
        return noteDao.searchNote(query)
    }

}