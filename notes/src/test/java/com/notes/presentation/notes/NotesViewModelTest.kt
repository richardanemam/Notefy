package com.notes.presentation.notes

import com.notes.common.fakes.getNoteFake
import com.notes.common.fakes.getNotesFake
import com.notes.domain.usecase.NotesUseCase
import io.mockk.Runs
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class NotesViewModelTest {

    private val useCase: NotesUseCase = mockk(relaxed = true)
    private lateinit var viewModel: NotesViewModel

    @OptIn(ExperimentalCoroutinesApi::class)
    private val unconfinedDispatcher = UnconfinedTestDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(unconfinedDispatcher)
        viewModel = NotesViewModel(useCase, unconfinedDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `init should the initial state as loading`() {
        // Then
        assertEquals(NotesState.OnLoading, viewModel.state.value)
    }

    @Test
    fun `fetchNotes should return a list of notes on success state`() {
        // Given
        every { useCase.getAllNotes() } returns flow { emit(getNotesFake()) }

        // When
        viewModel.fetchNotes()

        // Then
        assertEquals(
            NotesState.OnSuccess(notes = getNotesFake()),
            viewModel.state.value
        )
    }

    @Test
    fun `insertNote inserts a note to the database`() {
        // Given
        coEvery { useCase.insertNote(any()) } just Runs

        // When
        viewModel.insertNote(getNoteFake())

        // Then
        coVerify { useCase.insertNote(any()) }
    }

    @Test
    fun `deleteNote deletes a note from the database`() {
        // Given
        coEvery { useCase.deleteNote(any()) } just Runs

        // When
        viewModel.deleteNote(getNoteFake())

        // Then
        coVerify { useCase.deleteNote(any()) }
        assertEquals(
            NotesState.OnDeletedNote,
            viewModel.state.value
        )
    }

    @Test
    fun `restoreDeleteNote restores a deleted note to the database`() {
        // Given
        coEvery { useCase.insertNote(any()) } just Runs

        // When
        with(viewModel) {
            deleteNote(getNoteFake())
            restoreDeleteNote()
        }

        // Then
        coVerify { useCase.insertNote(any()) }
        assertEquals(
            NotesState.OnRestoredNote,
            viewModel.state.value
        )
    }
}