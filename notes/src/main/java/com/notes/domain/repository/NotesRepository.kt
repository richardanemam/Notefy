package com.notes.domain.repository

import com.notes.domain.model.Note
import kotlinx.coroutines.flow.Flow


interface NotesRepository {
    fun getAllNotes(): Flow<List<Note>>
    suspend fun insertNote(note: Note)

    suspend fun getNoteById(id: Int): Note?

    suspend fun deleteNote(note: Note)
}