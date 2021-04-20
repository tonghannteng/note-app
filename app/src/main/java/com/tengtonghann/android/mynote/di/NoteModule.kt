package com.tengtonghann.android.mynote.di

import android.app.Application
import com.tengtonghann.android.mynote.db.NoteDao
import com.tengtonghann.android.mynote.db.NoteDatabase
import com.tengtonghann.android.mynote.repository.NoteRepository
import com.tengtonghann.android.mynote.repository.INoteRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
@ExperimentalCoroutinesApi
class NoteModule {

    @Singleton
    @Provides
    fun provideDatabase(application: Application) = NoteDatabase.getInstance(application)

    @Singleton
    @Provides
    fun provideNoteDao(database: NoteDatabase) = database.getNoteDao()

    @Singleton
    @Provides
    fun injectRepository(noteDao: NoteDao) = NoteRepository(noteDao) as INoteRepository

}