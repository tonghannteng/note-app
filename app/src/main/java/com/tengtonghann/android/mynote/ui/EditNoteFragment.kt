package com.tengtonghann.android.mynote.ui

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.tengtonghann.android.mynote.R
import com.tengtonghann.android.mynote.databinding.FragmentEditNoteBinding
import com.tengtonghann.android.mynote.model.Note
import com.tengtonghann.android.mynote.viewmodel.NoteViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi

/**
 * TONGHANN TENG
 * 03/24/2021
 */
@ExperimentalCoroutinesApi
@AndroidEntryPoint
class EditNoteFragment : Fragment(R.layout.fragment_edit_note) {

    private var _binding: FragmentEditNoteBinding? = null
    private val binding get() = _binding!!

    private val args: EditNoteFragmentArgs by navArgs()
    private lateinit var currentNote: Note
    private val mViewModel: NoteViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentEditNoteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        currentNote = args.note!!

        binding.etNoteTitleUpdate.setText(currentNote.noteTitle)
        binding.etNoteBodyUpdate.setText(currentNote.noteBody)

        binding.fabDone.setOnClickListener {
            val title = binding.etNoteTitleUpdate.text.toString()
            val body = binding.etNoteBodyUpdate.text.toString()

            if (title != null) {
                val note = Note(currentNote.id, title, body)
                mViewModel.updateNote(note)

                view.findNavController().navigate(R.id.action_editNoteFragment_to_homeFragment)
            } else {
                Toast.makeText(context, "Enter a note title please", Toast.LENGTH_SHORT).show()
            }

        }

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()
        inflater.inflate(R.menu.menu_edit_note, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_delete -> {
                deleteNote()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteNote() {
        AlertDialog.Builder(requireContext()).apply {
            setTitle("Delete Note")
            setMessage("Are you sure you want to permanently delete this note?")
            setPositiveButton("Delete") { _, _ ->
                mViewModel.deleteNote(currentNote)
                view?.findNavController()?.navigate(
                    R.id.action_editNoteFragment_to_homeFragment
                )
            }
            setNegativeButton("Cancel", null)
        }.create().show()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}