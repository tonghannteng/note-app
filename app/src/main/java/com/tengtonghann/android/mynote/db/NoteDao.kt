package com.tengtonghann.android.mynote.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.tengtonghann.android.mynote.model.Note

@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note: Note)

    @Update
    suspend fun updateNote(note: Note)

    @Delete
    suspend fun deleteNote(note: Note)

    @Query("SELECT * FROM NOTES ORDER BY id DESC")
    fun getAllNotes(): LiveData<List<Note>>

    @Query("SELECT * FROM NOTES WHERE noteTitle LIKE :query OR noteBody LIKE :query")
    fun searchNote(query: String): LiveData<List<Note>>
}