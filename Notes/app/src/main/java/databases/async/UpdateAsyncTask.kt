package com.domash.notes.databases.async

import android.os.AsyncTask
import com.domash.notes.models.Note
import com.domash.notes.NoteDAO

class UpdateAsyncTask constructor(
    private val noteDao: NoteDAO
) : AsyncTask<Note, Void, Void>() {

    override fun doInBackground(vararg notes: Note): Void? {
        noteDao.updateNote(notes[0])
        return null
    }

}