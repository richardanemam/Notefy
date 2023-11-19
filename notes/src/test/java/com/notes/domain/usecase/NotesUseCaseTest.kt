package com.notes.domain.usecase

import com.notes.common.fakes.getNoteFake
import com.notes.common.fakes.getNotesFake
import com.notes.data.repository.NotesRepositoryImpl
import io.mockk.Runs
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@Suppress("INLINE_FROM_HIGHER_PLATFORM")
class NotesUseCaseTest {

    private val repositoryImpl: NotesRepositoryImpl = mockk(relaxed = true)
    private lateinit var useCase: NotesUseCase

    @Before
    fun setup() {
        useCase = NotesUseCase(repositoryImpl)
    }

    @Test
    fun `getAllNotes should return a flow with all notes`() = runTest {
        // Given (sets up the conditions for the test scenario)
        // Given the request for all notes...
        every { repositoryImpl.getAllNotes() } returns flow { emit(getNotesFake()) }

        // When (covers the action or event you are testing)
        // When use case requests all notes
        val firstFakeNote = useCase.getAllNotes().first()[0]
        val secondFakeNote = useCase.getAllNotes().first()[1]

        // Then (verification step: covers the results or consequences of the test scenario)
        verify { repositoryImpl.getAllNotes() }
        assertEquals(getNotesFake()[0], firstFakeNote)
        assertEquals(getNotesFake()[1], secondFakeNote)
    }

    @Test
    fun `getAllNotes should return a flow with an empty list`() = runTest {
        // Given
        every { repositoryImpl.getAllNotes() } returns flow { emit(emptyList()) }

        // When
        val isEmpty = useCase.getAllNotes().first().isEmpty()

        // Then
        verify { repositoryImpl.getAllNotes() }
        assertEquals(true, isEmpty)
    }

    @Test
    fun `insertNote should insert a note to the database`() = runTest {
        // Given
        coEvery { repositoryImpl.insertNote(any()) } just Runs

        // When
        useCase.insertNote(getNoteFake())

        // Then
        coVerify { repositoryImpl.insertNote(any()) }
    }

    @Test
    fun `deleteNote should delete a note from database`() = runTest {
        // Given
        coEvery { repositoryImpl.deleteNote(any()) } just Runs

        // When
        useCase.deleteNote(getNoteFake())

        // Then
        coVerify { repositoryImpl.deleteNote(any()) }
    }

    @Test
    fun `getNoteById should return a note based on its id`() = runTest {
        // Given
        coEvery { repositoryImpl.getNoteById(any()) } returns getNoteFake()

        // When
        val note = useCase.getNoteNyId(1)

        // Then
        coVerify { repositoryImpl.getNoteById(any()) }
        assertEquals(getNoteFake(), note)
    }
}