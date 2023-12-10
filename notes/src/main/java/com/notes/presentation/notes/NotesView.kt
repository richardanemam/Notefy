package com.notes.presentation.notes

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.notes.domain.model.Note

@Composable
fun NotesGrid(
    notes: List<Note>
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.fillMaxSize()
    ) {
        items(items = notes) {
            NoteItem(note = it) {
                // TODO navigate to edit note
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun previewNotesView() {
    NotesGrid(
        notes = listOf(
            Note(
                title = "Preview 1st Note",
                content = "Preview 1st note content",
                creationTimestamp = 10L,
                color = 40,
                id = 1
            ),
            Note(
                title = "Preview 2nd Note",
                content = "Preview 2nd note content",
                creationTimestamp = 10L,
                color = 4,
                id = 2
            ),
            Note(
                title = "Preview 3rd Note",
                content = "Preview 3rd note content",
                creationTimestamp = 10L,
                color = 40,
                id = 1
            ),
            Note(
                title = "Preview 4th Note",
                content = "Preview 4th note content",
                creationTimestamp = 10L,
                color = 4,
                id = 2
            )
        )
    )
}