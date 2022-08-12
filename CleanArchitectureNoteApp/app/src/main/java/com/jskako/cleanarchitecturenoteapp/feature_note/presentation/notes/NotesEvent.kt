package com.jskako.cleanarchitecturenoteapp.feature_note.presentation.notes

import com.jskako.cleanarchitecturenoteapp.feature_note.domain.module.Note
import com.jskako.cleanarchitecturenoteapp.feature_note.domain.util.NoteOrder

sealed class NotesEvent {
    data class Order(val noteOrder: NoteOrder): NotesEvent()
    data class DeleteNote(val note: Note): NotesEvent()
    object RestoreNote: NotesEvent()
    object ToggleOrderSection: NotesEvent()
}
