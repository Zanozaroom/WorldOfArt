package com.example.museumart_inner.ui.fragment

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.api_model.ui.ButtonsAction
import com.example.api_model.ui.OnScrollRecycler
import com.example.api_model.state.UIState
import com.example.api_model.ui.ViewWithRecycler
import com.example.museumart_inner.R
import com.example.museumart_inner.ui.viewmodel.MuseumBaseViewModel
import com.example.museumart_inner.ui.viewmodel.MuseumArtWorkViewModel
import com.example.museumart_inner.utill.CMArtWorkAdapter
import kotlinx.coroutines.flow.collectLatest

class MuseumArtWorkFragment : MuseumBaseFragment(R.layout.fragment_museum_list) {
    private lateinit var viewModelChicagoMuseum: MuseumArtWorkViewModel
    override var toolbar: Toolbar? = null
    override var viewModelBase: MuseumBaseViewModel? = null

    private val adapterRecyclerView: CMArtWorkAdapter by lazy {
        CMArtWorkAdapter(viewModelChicagoMuseum)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        viewModelChicagoMuseum =
            ViewModelProvider(this, viewModelFactory)[MuseumArtWorkViewModel::class.java]
        viewModelBase = viewModelChicagoMuseum
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        toolbar = view.findViewById(R.id.topAppBar)
        val viewWithRecycler = view.findViewById<ViewWithRecycler>(R.id.viewWithRecycler)
        toolbar?.inflateMenu(R.menu.toolbar_menu)
        toolbar?.setupWithNavController(findNavController(), appBarConfiguration!!)
        initMenuItemClickListener(toolbar!!)
        initRecyclerView(viewWithRecycler)

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
                viewModelChicagoMuseum.flowArtWorks.collectLatest {
                    if (it is UIState.Success) adapterRecyclerView.submitList(it.data)
                    viewWithRecycler.setStateView(it)
                }
        }
    }

    private fun initMenuItemClickListener(toolbar: Toolbar) {
        toolbar.setOnMenuItemClickListener {
            viewModelChicagoMuseum.clickOnMenuToolbar(it.itemId)
            true
        }
    }


    private fun initRecyclerView(viewWithRecycler: ViewWithRecycler) {
        val layoutManager = GridLayoutManager(requireActivity(), COUNT_FOR_GRID_RECYCLER)
        viewWithRecycler.setRecyclerView(
            adapter = adapterRecyclerView,
            layoutManager = layoutManager,
            typeLayoutManager = ViewWithRecycler.GRID_MANAGER,
            onScroll = object : OnScrollRecycler {
                override fun onScroll() {
                    viewModelChicagoMuseum.loadMore()
                }
            }
        )

        viewWithRecycler.setListenerErrorButton {
            when (it) {
                ButtonsAction.ERROR_LOAD -> viewModelChicagoMuseum.loadArtWork()
                ButtonsAction.ERROR_LOAD_MORE -> viewModelChicagoMuseum.loadMore()
            }
        }
    }

    companion object {
        private const val COUNT_FOR_GRID_RECYCLER = 2
    }


}
