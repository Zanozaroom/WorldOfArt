package com.example.search_inner.ui.fragment

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.core_di.ViewModelFactory
import com.example.search_inner.navigator.SearchNavigator
import com.example.search_inner.ui.viewmodel.SearchMuseumBaseViewModel
import com.example.search_inner.ui.viewmodel.SearchViewModelComponent
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

abstract class SearchFragmentBase(fragmentSearch: Int) : Fragment(fragmentSearch) {
    @Inject
    lateinit var navigator: SearchNavigator
    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    abstract var viewModelSearchBase: SearchMuseumBaseViewModel?
    private val componentViewModel: SearchViewModelComponent by viewModels()

    override fun onAttach(context: Context) {
        componentViewModel.searchComponent.inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navigator.navController = findNavController()
        setViewModelListener()

    }

    private fun setViewModelListener() {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModelSearchBase!!.observerMuseumMessageFlow.collectLatest {
                Snackbar.make(requireView(), it, Snackbar.LENGTH_SHORT).show()
            }
        }
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModelSearchBase!!.observerMuseumNavigationFlow.collectLatest {
                navigator.navigate(it)
            }
        }
    }

}