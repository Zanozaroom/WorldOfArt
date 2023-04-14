package com.example.museumart_inner.ui.fragment

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import com.example.core_di.ViewModelFactory
import com.example.museumart_inner.ui.viewmodel.MuseumBaseViewModel
import com.example.museumart_inner.ui.viewmodel.ChicagoViewModelComponent
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.flow.collectLatest
import com.example.museumart_inner.navigation.NavigatorMuseum
import javax.inject.Inject

abstract class MuseumBaseFragment(fragmentMuseumList: Int) : Fragment(fragmentMuseumList) {
    @Inject
    lateinit var navigatorMuseum: NavigatorMuseum
    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    abstract var viewModelBase: MuseumBaseViewModel?
    private val componentViewModel: ChicagoViewModelComponent by viewModels()
    abstract var toolbar: Toolbar?
    var appBarConfiguration: AppBarConfiguration? = null

    override fun onAttach(context: Context) {
        componentViewModel.chicagoArtComponent.inject(this)
        navigatorMuseum.navController = findNavController()
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        appBarConfiguration = AppBarConfiguration(
            setOf(
                com.example.navigation.R.id.fragmentMuseumList,
                com.example.navigation.R.id.favoriteArtFragment,
            )
        )
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModelBase!!.observerMessageFlow.collectLatest {
                Snackbar.make(requireView(), it, Snackbar.LENGTH_SHORT).show()
            }
        }

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModelBase!!.observerNavigationFlow.collectLatest {
                navigatorMuseum.navFlowMuseum(it)
            }
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        toolbar = null
        appBarConfiguration = null
        navigatorMuseum.navController = null
    }
}