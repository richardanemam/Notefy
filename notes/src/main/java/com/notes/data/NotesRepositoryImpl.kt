package com.notes.data

import com.notes.domain.model.Note
import com.notes.domain.repository.NotesRepository
import kotlinx.coroutines.flow.Flow

internal class NotesRepositoryImpl: NotesRepository {
    override fun getNotes(): Flow<List<Note>> {
        TODO("Not yet implemented")
    }

    override fun addNote(note: Note) {
        TODO("Not yet implemented")
    }

    override fun editNote(note: Note) {
        TODO("Not yet implemented")
    }

    override fun deleteNote(note: Note) {
        TODO("Not yet implemented")
    }
}