package com.notes.domain.model

data class Note(
    private val title: String,
    private val note: String,
    private val dueDate: Long,
    private val uid: String
)
