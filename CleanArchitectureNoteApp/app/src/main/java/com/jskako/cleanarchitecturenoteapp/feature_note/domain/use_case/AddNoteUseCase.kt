package com.jskako.cleanarchitecturenoteapp.feature_note.domain.use_case

import com.jskako.cleanarchitecturenoteapp.feature_note.domain.module.InvalidNoteException
import com.jskako.cleanarchitecturenoteapp.feature_note.domain.module.Note
import com.jskako.cleanarchitecturenoteapp.feature_note.domain.repository.NoteRepository

class AddNoteUseCase(
    private val repository: NoteRepository
) {

    @Throws(InvalidNoteException::class)
    suspend operator fun invoke(note: Note) {
        if(note.title.isBlank()) {
            throw InvalidNoteException("The title of the note cannot be empty.")
        }
        if(note.content.isBlank()) {
            throw InvalidNoteException("The content of the note cannot be empty.")
        }
        repository.insertNote(note)
    }
}