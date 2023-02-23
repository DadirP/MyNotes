package com.example.mynotes

import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
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

        holder.setListerner(note)

        holder.binding.tvDescription.text = note.description

        holder.binding.cbFinished.isChecked = note.isFinished

        if (note.isFinished){
            holder.binding.tvDescription.setTextSize(TypedValue.COMPLEX_UNIT_SP,12f)
        } else{
            holder.binding.tvDescription.setTextSize(TypedValue.COMPLEX_UNIT_SP,16f)
        }
    }

    override fun getItemCount(): Int = noteList.size

    fun add(note: Note) {
        noteList.add(note)
        notifyDataSetChanged()
    }

    fun remove(note: Note) {
        noteList.remove(note)
        notifyDataSetChanged()
    }


    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val binding = ItemNoteBinding.bind(view)

        fun setListerner(note: Note){

            binding.cbFinished.setOnClickListener {
                note.isFinished = (it as CheckBox).isChecked
                notifyDataSetChanged()
            }

            binding.root.setOnLongClickListener {
                listener.onLongClick(note)
                true
            }
        }
    }
}