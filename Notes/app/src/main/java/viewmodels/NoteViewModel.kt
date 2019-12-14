package com.domash.notes.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.domash.notes.databases.NoteRepository
import com.domash.notes.models.Note

class NoteViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: NoteRepository = NoteRepository(application)
    private val notes: List<Note> = TODO()

    fun insert(note: Note) {
        TODO()
    }

    fun update(note: Note) {
        TODO()
    }

    fun delete(note: Note) {
        TODO()
    }

    fun getNotes() : LiveData<List<Note>> {
        TODO()
    }




}