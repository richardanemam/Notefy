package com.notes.data.repository

import com.commom.di.IoDispatcher
import com.notes.domain.model.Note
import com.notes.domain.repository.NotesRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

internal class NotesRepositoryImpl @Inject constructor(
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : NotesRepository {
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