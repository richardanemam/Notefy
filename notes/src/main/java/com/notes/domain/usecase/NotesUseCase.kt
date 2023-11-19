package com.notes.domain.usecase

import com.notes.domain.model.Note
import com.notes.domain.repository.NotesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

internal class NotesUseCase @Inject constructor(
    private val repository: NotesRepository
) {
    fun getAllNotes(): Flow<List<Note>> =
        repository.getAllNotes()

    suspend fun insertNote(note: Note) {
        repository.insertNote(note)
    }

    suspend fun deleteNote(note: Note) {
        repository.deleteNote(note)
    }

    suspend fun getNoteNyId(id: Int): Note? =
        repository.getNoteById(id)
}