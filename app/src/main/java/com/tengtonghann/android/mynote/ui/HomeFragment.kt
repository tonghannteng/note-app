package com.tengtonghann.android.mynote.ui

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.tengtonghann.android.mynote.R
import com.tengtonghann.android.mynote.adapter.NoteAdapter
import com.tengtonghann.android.mynote.databinding.FragmentHomeBinding
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
class HomeFragment : Fragment(R.layout.fragment_home), SearchView.OnQueryTextListener {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val mViewModel: NoteViewModel by viewModels()
    private lateinit var mNoteAdapter: NoteAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpRecyclerView()

        binding.fabAddNoteButton.setOnClickListener {
            it.findNavController().navigate(R.id.action_homeFragment_to_addNoteFragment)
        }
    }

    private fun setUpRecyclerView() {
        mNoteAdapter = NoteAdapter()

        binding.recyclerView.apply {
            layoutManager = StaggeredGridLayoutManager(
                2,
                StaggeredGridLayoutManager.VERTICAL
            )
            setHasFixedSize(true)
            adapter = mNoteAdapter
        }

        activity?.let {

            mViewModel.getAllNotes()
            mViewModel.allNotes.observe(viewLifecycleOwner, { note ->
                mNoteAdapter.differ.submitList(note)
                updateUI(note)
            })
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu.clear()
        inflater.inflate(R.menu.home_menu, menu)
        val menuSearch = menu.findItem(R.id.menu_search).actionView as SearchView
        menuSearch.isSubmitButtonEnabled = false
        menuSearch.setOnQueryTextListener(this)

    }

    private fun updateUI(note: List<Note>) {
        if (note.isEmpty()) {
            binding.cardView.visibility = View.VISIBLE
            binding.recyclerView.visibility = View.GONE
        } else {
            binding.cardView.visibility = View.GONE
            binding.recyclerView.visibility = View.VISIBLE
        }
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        if (newText != null) {
            searchNote(newText)
        }
        return true
    }

    private fun searchNote(newText: String) {
        val searchQuery = "%$newText%"
        mViewModel.searchNote(searchQuery)
        mViewModel.searchNoteResult.observe(viewLifecycleOwner, { note ->
            mNoteAdapter.differ.submitList(note)
            updateUI(note)
        })

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}