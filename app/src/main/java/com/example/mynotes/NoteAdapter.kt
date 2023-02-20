package com.example.mynotes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mynotes.databinding.ItemNoteBinding

class NoteAdapter (var noteList:MutableList<Note>,private val listener : OnClickListener) :
    RecyclerView.Adapter<NoteAdapter.ViewHolder> (){



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_note,parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val note = noteList.get(position)

        holder.binding.tvDescription.text = note.description
    }

    override fun getItemCount(): Int = noteList.size


    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val binding = ItemNoteBinding.bind(view)
    }
}