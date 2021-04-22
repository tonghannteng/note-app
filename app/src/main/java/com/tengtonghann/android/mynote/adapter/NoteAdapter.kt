package com.tengtonghann.android.mynote.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.tengtonghann.android.mynote.databinding.NoteLayoutAdapterBinding
import com.tengtonghann.android.mynote.model.Note
import com.tengtonghann.android.mynote.ui.HomeFragmentDirections
import java.util.*

class NoteAdapter : RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {

    class NoteViewHolder(val binding: NoteLayoutAdapterBinding) :
        RecyclerView.ViewHolder(binding.root)

    private val differCallback =
        object : DiffUtil.ItemCallback<Note>() {
            override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
                return (oldItem.id == newItem.id) &&
                        (oldItem.noteTitle == newItem.noteTitle) &&
                        (oldItem.noteBody == newItem.noteBody)
            }

            override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
                return oldItem == newItem
            }

        }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        return NoteViewHolder(
            NoteLayoutAdapterBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val currentNote = differ.currentList[position]

        holder.binding.tvNoteTitle.text = currentNote.noteTitle
        holder.binding.tvNoteBody.text = currentNote.noteBody
        val random = Random()
        val color = Color.argb(
            255, random.nextInt(256),
            random.nextInt(256), random.nextInt(256)
        )

        holder.binding.ibColor.setBackgroundColor(color)
        holder.itemView.setOnClickListener { view ->
            val direction = HomeFragmentDirections
                .actionHomeFragmentToEditNoteFragment(currentNote)
            view.findNavController().navigate(direction)
        }
    }


    override fun getItemCount(): Int {
        return differ.currentList.size
    }
}