package com.notes.domain.usecase

import com.notes.domain.repository.NotesRepository
import javax.inject.Inject

internal class NotesUseCase @Inject constructor(
    private val repository: NotesRepository
) {

}