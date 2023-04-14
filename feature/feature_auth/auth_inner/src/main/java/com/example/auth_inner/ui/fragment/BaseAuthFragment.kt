package com.example.auth_inner.ui.fragment

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.annotation.LayoutRes
import androidx.constraintlayout.widget.Group
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.auth_inner.navigation.NavigatorAuth
import com.example.auth_inner.utill.TryDoItState
import com.example.auth_inner.utill.WaitUserDataState
import com.example.auth_inner.ui.viewmodel.LoginViewModel
import com.example.auth_inner.ui.viewmodel.LoginViewModelComponent
import com.example.core_di.ViewModelFactory
import com.example.navigation.NavigatorI
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

abstract class BaseAuthFragment(@LayoutRes layout: Int) : Fragment(layout) {
    @Inject
    lateinit var navigatorI: NavigatorI
    @Inject
    lateinit var navigatorAuth: NavigatorAuth
    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    lateinit var loginViewModel: LoginViewModel
    private val componentViewModel: LoginViewModelComponent by viewModels()

    override fun onAttach(context: Context) {
        componentViewModel.loginComponent.inject(this)
        super.onAttach(context)
        loginViewModel =
            ViewModelProvider(this, viewModelFactory)[LoginViewModel::class.java]
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navigatorAuth.navController = findNavController()
        collectViewModelEvents()
    }

    private fun collectViewModelEvents() {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            loginViewModel.observerNavigationFlow.collectLatest {
                navigatorI.navigateToFlow(it)
            }
        }
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            loginViewModel.observerAuthNavigationFlow.collectLatest {
                navigatorAuth.navigate(it)
            }
        }
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            loginViewModel.observerMessageFlow.collectLatest {
                Snackbar.make(requireView(), it, Snackbar.LENGTH_SHORT).show()
            }
        }
    }
}

