package com.notes.presentation.notes

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.notes.domain.model.Note

@Composable
fun NoteItem(
    note: Note,
    onItemClick: () -> Unit
) {
    ElevatedCard(
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        modifier = Modifier
            .clickable { onItemClick() }
            .size(width = 200.dp, height = 200.dp)
            .padding(8.dp)
    ) {
        Text(
            text = note.title,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            fontSize = 24.sp,
            textAlign = TextAlign.Start,
            modifier = Modifier.padding(start = 16.dp, top = 16.dp, end = 16.dp, bottom = 4.dp)
        )

        Text(
            text = note.content,
            maxLines = 10,
            overflow = TextOverflow.Ellipsis,
            fontSize = 16.sp,
            textAlign = TextAlign.Start,
            modifier = Modifier.padding(start = 16.dp, top = 4.dp, end = 16.dp, bottom = 4.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewNoteItem(@PreviewParameter(LoremIpsum::class) content: String) {
    NoteItem(
        note = Note(
            title = "Preview Note",
            content = content,
            creationTimestamp = 10L,
            color = 40,
            id = 1
        )
    ) {}
}