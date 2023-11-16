package com.notes.data.repository

import com.commom.di.IoDispatcher
import com.notes.data.datasource.NoteDao
import com.notes.domain.model.Note
import com.notes.domain.repository.NotesRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class NotesRepositoryImpl @Inject constructor(
    private val dao: NoteDao,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : NotesRepository {
    override fun getAllNotes(): Flow<List<Note>> = dao.getAllNotes()

    override suspend fun insertNote(note: Note) = withContext(ioDispatcher) {
        dao.insertNote(note)
    }

    override suspend fun getNoteById(id: Int): Note? = withContext(ioDispatcher) {
        dao.getNoteById(id)
    }

    override suspend fun deleteNote(note: Note) = withContext(ioDispatcher) {
        dao.deleteNote(note)
    }
}