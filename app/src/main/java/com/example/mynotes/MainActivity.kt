package com.example.mynotes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mynotes.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), OnClickListener {
    private lateinit var binding: ActivityMainBinding
        private lateinit var noteAdapter: NoteAdapter
        private lateinit var noteFinishedAdapter: NoteAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        noteAdapter = NoteAdapter(mutableListOf(), this)
        binding.rvNotes.apply {
            layoutManager = LinearLayoutManager (this@MainActivity)
            adapter = noteAdapter
        }

        noteFinishedAdapter = NoteAdapter(mutableListOf(), this)
        binding.rvNotesFinished.apply {
            layoutManager = LinearLayoutManager (this@MainActivity)
            adapter = noteFinishedAdapter
        }

        binding.btnAdd.setOnClickListener{
            if (binding.etDescription.text.toString().isNotBlank()){
                val note =  Note((noteAdapter.itemCount + 1).toLong(),
                    binding.etDescription.text.toString().trim())
                addNoteAuto(note)
                binding.etDescription.text?.clear()
            } else {
                binding.etDescription.error = getString(R.string.validation_field_required)
            }
        }
    }

    override fun onStart() {
        super.onStart()
        getData()
    }

    private fun getData () {
        val data = mutableListOf(
        Note(1,"Estudiar"),
        Note(2,"Ir al mercado"),
        Note(3,"Comprar arroz",true))
        data.forEach { note ->  
            addNoteAuto(note)
        }
    }
    private fun addNoteAuto(note: Note) {
        if (note.isFinished) {
            noteFinishedAdapter.add(note)
        } else {
            noteAdapter.add(note)
        }
    }

    private fun deleteNoteAuto(note: Note) {
        if (note.isFinished){
                noteAdapter.remove(note)}
        else {
            noteFinishedAdapter.remove(note)
        }
    }

    override fun onChecked(note: Note) {
        deleteNoteAuto(note)
        addNoteAuto(note)
    }

    override fun onLongClick(note: Note, currentAdapter: NoteAdapter) {
        val builder = AlertDialog.Builder(this)
        .setTitle(getString(R.string.dialog_title))
        .setPositiveButton(getString(R.string.dialog_ok),{ dialogInterface, i->
            currentAdapter.remove(note)
        })
        .setNegativeButton(getString(R.string.gialog_cancel),null)
        builder.create().show()
    }


}
