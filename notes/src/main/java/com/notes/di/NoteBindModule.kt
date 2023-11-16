package com.notes.di

import com.notes.data.repository.NotesRepositoryImpl
import com.notes.domain.repository.NotesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class NoteBindModule {

    @Binds
    abstract fun bindsNoteRepository(
        notesRepositoryImpl: NotesRepositoryImpl
    ): NotesRepository
}