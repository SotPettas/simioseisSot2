package com.assigment.simioseiSot.ViewModel

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import androidx.recyclerview.widget.RecyclerView
import com.assigment.simioseiSot.databinding.UpNotesItemBinding


class UpNoteAdapter(private val notes: List<NoteItem>, private val itemClickListener :OnItemClickListener ) :
    RecyclerView.Adapter<UpNoteAdapter.UpNoteViewHolder>() {
    interface OnItemClickListener{
        fun onDeleteClick(noteId:String)
        fun onUpdateClick(noteId:String, title:String,description: String)

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UpNoteViewHolder {

        val binding :UpNotesItemBinding = UpNotesItemBinding.inflate(LayoutInflater.from(parent.context), parent, false )
        return UpNoteViewHolder(binding)

    }



    override fun onBindViewHolder(holder: UpNoteViewHolder, position: Int) {
         val upnote :NoteItem = notes[position]
          holder.bind(upnote)
          holder.binding.updateUButton.setOnClickListener {

              itemClickListener.onUpdateClick(upnote.noteId,upnote.title,upnote.description)
          }
        holder.binding.deleteUButton.setOnClickListener {

              itemClickListener.onDeleteClick(upnote.noteId)
          }


    }

    override fun getItemCount(): Int {
        return notes.size

    }

    class UpNoteViewHolder(val binding: UpNotesItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(note: NoteItem) {

         binding.titleTextView.text = note.title
         binding.descriptionTextView.text = note.description




        }


    }
}