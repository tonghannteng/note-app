package com.tengtonghann.android.mynote.repository

import androidx.lifecycle.LiveData
import com.tengtonghann.android.mynote.db.NoteDao
import com.tengtonghann.android.mynote.model.Note
import kotlinx.coroutines.ExperimentalCoroutinesApi
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

    override fun getAllNotes(): LiveData<List<Note>> {
        return noteDao.getAllNotes()
    }

    override fun searchNote(query: String): LiveData<List<Note>> {
        return noteDao.searchNote(query)
    }

}