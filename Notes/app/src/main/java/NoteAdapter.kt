package com.domash.notes

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import com.domash.notes.Models.Note


class NoteAdapter : RecyclerView.Adapter<NoteAdapter.NoteHolder>() {

    private val notes = listOf<Note>(
            Note(1, "fjdk", "fdkfjkdk", listOf("fjkdfjk")),
            Note(2, "fjdk", "fdkfjkdk", listOf("fjkdfjk")),
            Note(3, "fjdk", "fdkfjkdk", listOf("fjkdfjk")),
            Note(4, "fjdk", "fdkfjkdk", listOf("fjkdfjk")),
            Note(5, "fjdk", "fdkfjkdk", listOf("fjkdfjk")),
            Note(6, "fjdk", "fdkfjkdk", listOf("fjkdfjk")),
            Note(7, "fjdk", "fdkfjkdk", listOf("fjkdfjk")),
            Note(8, "fjdk", "fdkfjkdk", listOf("fjkdfjk")),
            Note(9, "fjdk", "fdkfjkdk", listOf("fjkdfjk")),
            Note(10, "fjdk", "fdkfjkdk", listOf("fjkdfjk")),
            Note(5, "fjdk", "fdkfjkdk", listOf("fjkdfjk")),
            Note(6, "fjdk", "fdkfjkdk", listOf("fjkdfjk")),
            Note(7, "fjdk", "fdkfjkdk", listOf("fjkdfjk")),
            Note(8, "fjdk", "fdkfjkdk", listOf("fjkdfjk"))

    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.note_item, parent, false)
        return NoteHolder(view)
    }

    override fun onBindViewHolder(holder: NoteHolder, position: Int) {
        holder.titleTextView.text = notes[position].title
        holder.bodyTextView.text  = notes[position].body
    }

    override fun getItemCount(): Int {
        return notes.size
    }

    class NoteHolder(view: View) : RecyclerView.ViewHolder(view) {

        internal val titleTextView: TextView
        internal val bodyTextView:  TextView

        init {

            titleTextView = view.findViewById(R.id.title_text)
            bodyTextView  = view.findViewById(R.id.body_text)

        }

    }

}