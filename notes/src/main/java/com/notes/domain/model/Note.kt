package com.notes.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.commom.ui.theme.Pink40
import com.commom.ui.theme.Purple40
import com.commom.ui.theme.Purple80
import com.commom.ui.theme.PurpleGrey40
import com.commom.ui.theme.PurpleGrey80

@Entity
data class Note(
    val title: String,
    val content: String,
    val creationTimestamp: Long,
    val color: Int,
    @PrimaryKey val id: Int? = null
) {
    companion object {
        val noteColor = listOf(Purple40, Purple80, PurpleGrey40, PurpleGrey80, Pink40)
    }
}
