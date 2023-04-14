package com.example.favorite_inner.ui.fragment

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
import com.example.favorite_inner.navigator.FavoriteNavigator
import com.example.favorite_inner.ui.viewmodel.FavoriteViewModelComponent
import com.example.favorite_inner.ui.viewmodel.UserFavoriteBaseViewModel
import com.example.navigation.R
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

abstract class FavoriteArtFragmentBase(fragmentFavorite: Int) : Fragment(fragmentFavorite) {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    @Inject
    lateinit var navigator: FavoriteNavigator
    private val componentViewModel: FavoriteViewModelComponent by viewModels()
    abstract var toolbar: Toolbar?
    abstract var baseViewModel: UserFavoriteBaseViewModel?
    var appBarConfiguration: AppBarConfiguration? = null

    override fun onAttach(context: Context) {
        componentViewModel.favoriteComponent.inject(this)
        super.onAttach(context)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navigator.navController = findNavController()
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.fragmentMuseumList,
                R.id.favoriteArtFragment,
            )
        )
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            baseViewModel?.observerNavigationFlow?.collect {
                navigator.navigate(it)
            }
        }
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            baseViewModel?.observerMessageFlow?.collectLatest {
                Snackbar.make(requireView(), it, Snackbar.LENGTH_SHORT).show()
            }
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        toolbar = null
        appBarConfiguration = null
        baseViewModel = null
    }
}