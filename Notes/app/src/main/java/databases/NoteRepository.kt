package com.domash.notes.databases

import android.app.Application
import androidx.lifecycle.LiveData
import com.domash.notes.NoteDAO
import com.domash.notes.databases.async.DeleteAsyncTask
import com.domash.notes.databases.async.InsertAsyncTask
import com.domash.notes.databases.async.UpdateAsyncTask
import com.domash.notes.models.Note

class NoteRepository  {

    private val noteDAO: NoteDAO
    private val notes: LiveData<List<Note>>

    constructor(application: Application) {

        val database = NoteDatabase.getInstance(application)
        noteDAO = database.noteDao()
        notes = noteDAO.getNotes()

    }

    fun insert(note: Note) {
        InsertAsyncTask(noteDAO).execute(note)
    }

    fun update(note: Note) {
        UpdateAsyncTask(noteDAO).execute(note)
    }

    fun delete(note: Note) {
        DeleteAsyncTask(noteDAO).execute(note)
    }

    fun getNotes() : LiveData<List<Note>> {
        return notes
    }



}