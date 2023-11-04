package com.notes.domain.repository

import com.notes.domain.model.Note
import kotlinx.coroutines.flow.Flow


interface NotesRepository {
    fun getNotes(): Flow<List<Note>>
    fun addNote(note: Note)
    fun editNote(note: Note)
    fun deleteNote(note: Note)
}