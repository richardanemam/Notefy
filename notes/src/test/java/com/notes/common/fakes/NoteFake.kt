package com.notes.common.fakes

import com.notes.domain.model.Note

/**
 * Test double is a dependency that provided to the system under test (SUT) created to look and act
 * as components of the app, however they're created to provide specific behaviour and data.
 *
 * Check the test doubles types below
 * @Link https://developer.android.com/training/testing/fundamentals/test-doub
 * @author Richard Anemam
 */
internal fun getNotesFake() = listOf(
    Note(
        title = "Fake note",
        content = "Fake note content",
        creationTimestamp = 100,
        color = 10,
        id = 1
    ),
    Note(
        title = "Fake note 2",
        content = "Fake note content 2",
        creationTimestamp = 101,
        color = 11,
        id = 2
    )
)

internal fun getNoteFake() = Note(
    title = "Fake note",
    content = "Fake note content",
    creationTimestamp = 100,
    color = 10,
    id = 1
)