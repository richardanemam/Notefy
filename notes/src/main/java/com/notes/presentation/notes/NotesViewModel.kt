package com.notes.presentation.notes

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.commom.di.IoDispatcher
import com.notes.domain.model.Note
import com.notes.domain.usecase.NotesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val LOG_TAG = "NotesVM"

@HiltViewModel
internal class NotesViewModel @Inject constructor(
    private val useCase: NotesUseCase,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _state: MutableStateFlow<NotesState> = MutableStateFlow(NotesState.OnLoading)
    val state: StateFlow<NotesState> = _state.asStateFlow()

    //@VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    private var lastDeletedNote: Note? = null

    fun fetchNotes() {
        viewModelScope.launch {
            useCase
                .getAllNotes()
                .flowOn(ioDispatcher)
                .catch {
                    Log.e(LOG_TAG, "unable to fetch all notes due to: ${it.message}")
                    _state.value = NotesState.OnError
                }
                .collect {
                    _state.value = NotesState.OnSuccess(notes = it)
                }
        }
    }

    fun insertNote(note: Note) {
        viewModelScope.launch {
            useCase.insertNote(note)
        }
    }

    fun deleteNote(note: Note) {
        viewModelScope.launch {
            useCase.deleteNote(note)
            lastDeletedNote = note
            _state.value = NotesState.OnDeletedNote
        }
    }

    fun restoreDeleteNote() {
        viewModelScope.launch {
            lastDeletedNote?.let { useCase.insertNote(it) }
            lastDeletedNote = null
            _state.value = NotesState.OnRestoredNote
        }
    }
}

sealed class NotesState {
    data class OnSuccess(val notes: List<Note> = emptyList()) : NotesState()
    object OnDeletedNote : NotesState()
    object OnRestoredNote : NotesState()
    object OnError : NotesState()
    object OnLoading : NotesState()
}