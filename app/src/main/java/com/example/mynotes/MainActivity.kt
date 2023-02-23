package com.example.mynotes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
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
            Note(2,"Ir al mercado"),
            Note(3,"Comprar arroz",true))

        noteAdapter = NoteAdapter(data, this)
        binding.recycleView.apply {
            layoutManager = LinearLayoutManager (this@MainActivity)
            adapter = noteAdapter
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

    private fun addNoteAuto(note: Note) {
        noteAdapter.add(note)
    }

    private fun deleteNoteAuto(note: Note) {
        val builder = AlertDialog.Builder(this)
            .setTitle(getString(R.string.dialog_title))
            .setPositiveButton(getString(R.string.dialog_ok),{ dialogInterface, i->
                noteAdapter.remove(note)
            })
            .setNegativeButton(getString(R.string.gialog_cancel),null)
        builder.create().show()
    }

    override fun onChecked(note: Note) {

    }

    override fun onLongClick(note: Note) {
        deleteNoteAuto(note)
    }


}
