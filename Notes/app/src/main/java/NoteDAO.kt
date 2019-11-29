package com.domash.notes

import androidx.room.*
import com.domash.notes.Models.Note

@Dao
interface NoteDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNote(note: Note)

    @Update
    fun updateNote(note: Note)

    @Delete
    fun deleteNote(note: Note)

    @Query("SELECT * FROM Notes")
    fun getNotes() : List<Note>

}