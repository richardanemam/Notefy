package com.notes.data.datasource

import androidx.room.Database
import com.notes.domain.model.Note

@Database(entities = [Note::class], version = 1)
abstract class NoteDatabase {
    abstract fun noteDao(): NoteDao
}