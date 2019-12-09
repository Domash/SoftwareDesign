package com.domash.notes

import android.content.res.Configuration
import android.os.Bundle
import android.widget.GridLayout
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomappbar.BottomAppBar

class MainActivity : AppCompatActivity() {

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

        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = NoteAdapter()

       // val bottomBar: BottomAppBar = findViewById(R.id.bottom_app_bar)
       // setSupportActionBar(bottomBar)

    }
}