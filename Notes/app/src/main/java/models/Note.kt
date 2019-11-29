package com.domash.notes.Models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "Notes", indices = [(Index(value = ["id"], unique = true))])
data class Note(
    @PrimaryKey(autoGenerate = true) var id:    Long,
    @ColumnInfo(name = "title")      var title: String,
    @ColumnInfo(name = "body")       var body:  String,
    @ColumnInfo(name = "tags")       var tags:  List<String>
)