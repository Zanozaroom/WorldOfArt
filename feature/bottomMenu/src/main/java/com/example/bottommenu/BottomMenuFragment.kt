package com.example.bottommenu

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView

internal class BottomMenuFragment : Fragment(R.layout.fragment_bottom_menu_layout) {
    var navController: NavController? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navHostFragment =
            childFragmentManager.findFragmentById(R.id.mainFragmentAHost) as NavHostFragment
        val btmNav = view.findViewById<BottomNavigationView>(R.id.bottomNavigation)
        navController = navHostFragment.findNavController()
        btmNav.setupWithNavController(navController!!)
        setNavListenerForBottomMenu(navController!!, btmNav)
    }

    private fun setNavListenerForBottomMenu(
        navController: NavController,
        btmNav: BottomNavigationView
    ) {
        navController.addOnDestinationChangedListener { controller, _, _ ->
            when (controller.previousBackStackEntry?.destination?.id) {
                com.example.navigation.R.id.favoriteArtFragment -> {
                    btmNav.menu.findItem(com.example.navigation.R.id.favorite_art_graph).isChecked =
                        true
                }
            }
        }
    }

    override fun onDetach() {
        super.onDetach()
        navController = null
    }
}
