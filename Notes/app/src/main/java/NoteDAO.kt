package com.domash.notes

import androidx.lifecycle.LiveData
import androidx.room.*
import com.domash.notes.models.Note

@Dao
interface NoteDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNote(note: Note)

    @Update
    fun updateNote(note: Note)

    @Delete
    fun deleteNote(note: Note)

    @Query("SELECT * FROM Notes")
    fun getNotes() : LiveData<List<Note>>

}