package com.example.mynotes

interface OnClickListener {
    fun onChecked (note: Note)
    fun onLongClick(note: Note)
}