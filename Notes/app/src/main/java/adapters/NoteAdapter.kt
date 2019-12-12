package com.domash.notes.adapters

import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import com.domash.notes.R
import com.domash.notes.models.Note
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup


class NoteAdapter : RecyclerView.Adapter<NoteAdapter.NoteHolder>() {

    companion object {
        private const val TAG = "NoteAdapter"
    }

    private lateinit var listener: OnNoteListener

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
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.note_item, parent, false)
        return NoteHolder(view)
    }

    override fun onBindViewHolder(holder: NoteHolder, position: Int) {
        holder.bind(notes[position])
    }

    override fun getItemCount(): Int {
        return notes.size
    }

    inner class NoteHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val titleTextView: TextView = view.findViewById(R.id.title_text)
        private val bodyTextView: TextView  = view.findViewById(R.id.body_text)
        private val chipGroup: ChipGroup = view.findViewById(R.id.chip_group)

        init {
            view.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    listener.onNoteClick((notes[position]))
                    Log.d(TAG, "OnClick $adapterPosition")
                }
            }
        }

        internal fun bind(note: Note) {

            titleTextView.text = note.title
            bodyTextView.text  = note.body

            chipGroup.removeAllViews()
            note.tags.forEach { tag ->
                val chip = Chip(chipGroup.context)
                chip.text = tag
                chipGroup.addView(chip)
            }

        }

    }

    interface OnNoteListener {
        fun onNoteClick(note: Note)
    }

    fun setNoteListener(listener: OnNoteListener) {
        this.listener = listener
    }

}

