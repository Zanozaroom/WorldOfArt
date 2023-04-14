package com.example.search_inner.ui.fragment

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ImageButton
import androidx.core.view.children
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.GridLayoutManager
import com.example.api_model.ui.OnScrollRecycler
import com.example.api_model.state.UIState
import com.example.api_model.ui.ViewWithRecycler
import com.example.search_inner.ui.utills.Searchdapter
import com.example.search_inner.ui.viewmodel.SearchMuseumViewModel
import com.example.search_inner.R
import com.example.core_api.utills.hideKeyAndStartAction
import com.example.search_inner.ui.viewmodel.SearchMuseumBaseViewModel
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


class SearchFragment : SearchFragmentBase(R.layout.fragment_search) {
    override var viewModelSearchBase: SearchMuseumBaseViewModel? = null
    private lateinit var searchViewModel: SearchMuseumViewModel
    private val adapterSearch: Searchdapter by lazy {
        Searchdapter(searchViewModel)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        searchViewModel =
            ViewModelProvider(this, viewModelFactory)[SearchMuseumViewModel::class.java]
        viewModelSearchBase = searchViewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val edittext = view.findViewById<EditText>(R.id.searchText)
        val chips = view.findViewById<ChipGroup>(R.id.layoutChips)
        val recyclerView = view.findViewById<ViewWithRecycler>(R.id.viewWithRecycler)
        val iconExit = view.findViewById<ImageButton>(R.id.iconExit)
        setEditTextListener(edittext)
        setChipsListener(chips)
        initRecycler(recyclerView)

        iconExit.setOnClickListener {
            searchViewModel.onClickIconClosed()
        }

        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                searchViewModel.searchArtWorks.collectLatest {
                    recyclerView.setStateView(it)
                    if (it is UIState.Success) adapterSearch.submitList(it.data)
                }
            }
        }
    }

    private fun initRecycler(viewWithRecycler: ViewWithRecycler) {
        val layoutManager = GridLayoutManager(requireActivity(), 2)
        viewWithRecycler.setRecyclerView(
            adapter = adapterSearch,
            layoutManager = layoutManager,
            typeLayoutManager = ViewWithRecycler.GRID_MANAGER,
            onScroll = object : OnScrollRecycler {
                override fun onScroll() {
                    searchViewModel.loadSearchMore()
                }
            }
        )

        viewWithRecycler.setListenerErrorButton {
            searchViewModel.clickOnButtonView(it)
        }
    }

    private fun setEditTextListener(searchText: EditText) {
        searchText.hideKeyAndStartAction{
            searchViewModel.setQuery(searchText.text.toString())
        }
    }

    private fun setChipsListener(layoutChips: ChipGroup) {
        layoutChips.setOnCheckedStateChangeListener { group, idChecked ->
            if (idChecked.isNotEmpty()) {
                val chip = group.children
                    .filter {
                        (it as Chip).isChecked
                    }
                    .first()
                searchViewModel.setQuery((chip as Chip).text.toString())
            } else {
                searchViewModel.setQuery(SearchMuseumViewModel.EMPTY_SEARCH)
            }
        }
    }
}