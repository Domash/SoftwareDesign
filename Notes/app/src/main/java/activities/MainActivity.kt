package com.domash.notes.activities

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.domash.notes.R
import com.domash.notes.adapters.NoteAdapter
import com.domash.notes.models.Note
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private val noteAdapter = NoteAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView: RecyclerView = findViewById(R.id.recycler_view)

        when(resources.configuration.orientation) {

            Configuration.ORIENTATION_PORTRAIT -> {
                recyclerView.layoutManager = LinearLayoutManager(this)
            }

            Configuration.ORIENTATION_LANDSCAPE -> {
                recyclerView.layoutManager = GridLayoutManager(this, 2)
            }

        }

        noteAdapter.setNoteListener(object : NoteAdapter.OnNoteListener {
            override fun onNoteClick(note: Note) {
                val intent = Intent(this@MainActivity, NoteActivity::class.java)
                intent.putExtra("ID", note.id)
                startActivity(intent)
            }
        })

        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = noteAdapter

        val fab: FloatingActionButton = findViewById(R.id.fab)

        fab.setOnClickListener {
            val intent = Intent(applicationContext, NoteActivity::class.java)
            startActivity(intent)
        }

        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) = when {
                dy > 0 -> fab.hide()
                else -> fab.show()
            }
        })

    }
}