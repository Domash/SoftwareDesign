package com.domash.notes

import android.content.Context
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.domash.notes.Models.Note
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup


class NoteAdapter : RecyclerView.Adapter<NoteAdapter.NoteHolder>() {

    private lateinit var context: Context

    private val notes = listOf<Note>(
            Note(1, "aaa", "fdkfjkdk", listOf("1", "11", "111", "23232323")),
            Note(2, "fjdk", "fdkfjkdk", listOf("2")),
            Note(3, "fjdk", "fdkfjkdk", listOf("3")),
            Note(4, "fjdk", "fdkfjkdk", listOf("4")),
            Note(5, "fjdk", "fdkfjkdk", listOf("5")),
            Note(6, "fjdk", "fdkfjkdk", listOf("6")),
            Note(7, "fjdk", "fdkfjkdk", listOf("7")),
            Note(8, "fjdk", "fdkfjkdk", listOf("8")),
            Note(9, "fjdk", "fdkfjkdk", listOf("9")),
            Note(10, "fjdk", "fdkfjkdk", listOf("10")),
            Note(11, "fjdk", "fdkfjkdk", listOf("11")),
            Note(12, "fjdk", "fdkfjkdk", listOf("12")),
            Note(13, "fjdk", "fdkfjkdk", listOf("13")),
            Note(14, "fjdk", "fdkfjkdk", listOf("14"))
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteHolder {

        context = parent.context

        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.note_item, parent, false)
        return NoteHolder(view)

    }

    override fun onBindViewHolder(holder: NoteHolder, position: Int) {

        holder.titleTextView.text = notes[position].title
        holder.bodyTextView.text  = notes[position].body

        holder.chipGroup.removeAllViews()
        notes[position].tags.forEach { tag ->
            val chip = Chip(context)
            chip.text = tag
            holder.chipGroup.addView(chip)
        }

    }

    override fun getItemCount(): Int {
        return notes.size
    }

    class NoteHolder(view: View) : RecyclerView.ViewHolder(view) {

        internal val titleTextView: TextView
        internal val bodyTextView:  TextView
        internal val chipGroup:     ChipGroup

        init {

            titleTextView = view.findViewById(R.id.title_text)
            bodyTextView  = view.findViewById(R.id.body_text)
            chipGroup     = view.findViewById(R.id.chip_group)

        }

    }

}