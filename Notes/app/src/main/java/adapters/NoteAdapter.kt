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

    private lateinit var notes: List<Note>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.note_item, parent, false)
        return NoteHolder(view)
    }

    override fun onBindViewHolder(holder: NoteHolder, position: Int) {
        holder.bind(notes[position])
    }

    fun setNotes(notes: List<Note>) {
        this.notes = notes
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

