package com.domash.notes.databases


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.domash.notes.models.Note
import com.domash.notes.NoteDAO

@Database(entities = [Note::class], version = 1)
abstract class NoteDatabase : RoomDatabase() {

    abstract fun noteDao() : NoteDAO

    companion object {

        private var INSTANCE: NoteDatabase? = null

        @Synchronized
        fun getInstance(context: Context): NoteDatabase {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context.applicationContext,
                        NoteDatabase::class.java, "Notes.db")
                        .build()
            }
            return INSTANCE!!
        }

    }

}