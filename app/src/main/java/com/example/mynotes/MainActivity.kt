package com.example.mynotes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mynotes.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), OnClickListener {
    private lateinit var binding : ActivityMainBinding
        private lateinit var noteAdapter: NoteAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val data = mutableListOf(
            Note(1,"Estudiar"),
            Note(2,"Ir al mercado"))

        noteAdapter = NoteAdapter(data, this)
        binding.recycleView.apply {
            layoutManager = LinearLayoutManager (this@MainActivity)
            adapter = noteAdapter
        }

    }

    override fun onLongClick(note: Note) {

    }
}