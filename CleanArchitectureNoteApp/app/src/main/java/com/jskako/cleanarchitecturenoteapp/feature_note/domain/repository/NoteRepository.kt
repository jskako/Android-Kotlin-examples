package com.jskako.cleanarchitecturenoteapp.feature_note.domain.repository

import com.jskako.cleanarchitecturenoteapp.feature_note.domain.module.Note
import kotlinx.coroutines.flow.Flow

interface NoteRepository {

    fun getNotes(): Flow<List<Note>>

    suspend fun getNoteById(id: Int): Note?

    suspend fun insertNote(note: Note)

    suspend fun deleteNote(note: Note)

}