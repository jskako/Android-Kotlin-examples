package com.jskako.cleanarchitecturenoteapp.feature_note.presentation.notes.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.jskako.cleanarchitecturenoteapp.feature_note.domain.module.Note

@Composable
fun NoteItem(
    note: Note,
    modifier: Modifier = Modifier,
    cornerRadius: Dp = 10.dp,
    cutCornerSize: Dp = 30.dp,
    onDeleteClick: () -> Unit
) {

}