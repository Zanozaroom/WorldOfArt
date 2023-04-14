package com.example.project_alfa_angry_snake.fragment

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.core_di.ViewModelFactory
import com.example.navigation.NavigationBottomMenuFlow
import com.example.navigation.NavigationLoginFlow
import com.example.navigation.NavigatorI
import com.example.project_alfa_angry_snake.MainActivity
import com.example.project_alfa_angry_snake.R
import com.example.project_alfa_angry_snake.viewmodel.StartFragmentViewModel
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

class StartFragment : Fragment(R.layout.fragment_start) {
    @Inject
    lateinit var navigator: NavigatorI
    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var startFragmentViewModel: StartFragmentViewModel

    override fun onAttach(context: Context) {
        (requireActivity() as MainActivity).activityComponent!!.inject(this)
        super.onAttach(context)
        startFragmentViewModel =
            ViewModelProvider(this, viewModelFactory)[StartFragmentViewModel::class.java]
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
           startFragmentViewModel.getThemeDayNight().collectLatest {
                AppCompatDelegate.setDefaultNightMode(it)
            }
        }
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            startFragmentViewModel.eventNavigation.collectLatest {
                navigator.navigateToFlow(it)
            }
        }
    }
}