package com.example.mynotes

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.mynotes.databinding.ItemNoteBinding

class NoteAdapter {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val binding = ItemNoteBinding.bind(view)
    }
}