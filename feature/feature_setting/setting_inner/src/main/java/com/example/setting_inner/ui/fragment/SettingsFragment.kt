package com.example.setting_inner.ui.fragment

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.core_di.ViewModelFactory
import com.example.navigation.NavigatorI
import com.example.setting_inner.ui.viewmodel.SettingViewModel
import com.example.setting_inner.ui.viewmodel.SettingViewModelComponent
import com.example.setting_inner.R
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

class SettingsFragment : Fragment(R.layout.fragment_settings) {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    @Inject
    lateinit var navigator: NavigatorI
    private lateinit var settingViewModel: SettingViewModel
    private val componentViewModel: SettingViewModelComponent by viewModels()

    override fun onAttach(context: Context) {
        componentViewModel.settingsComponent.inject(this)
        super.onAttach(context)
        settingViewModel =
            ViewModelProvider(this, viewModelFactory)[SettingViewModel::class.java]
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val buttonLogOut = view.findViewById<Button>(R.id.btnLogOut)
        val buttonSetThemeMode = view.findViewById<Button>(R.id.btnSetThemeMode)
        buttonLogOut.setOnClickListener {
            settingViewModel.logOutUser()
        }
        buttonSetThemeMode.setOnClickListener {
            val dialogFragment = ThemeDialogFragment()
            dialogFragment.show(childFragmentManager, ThemeDialogFragment.TAG)
            setDialogListener(dialogFragment)
        }
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            settingViewModel.observerMuseumNavigationFlow.collectLatest {
                navigator.navigateToFlow(it)
            }
        }
    }

    private fun setDialogListener(dialogFragment: ThemeDialogFragment) {
        childFragmentManager.setFragmentResultListener(
            ThemeDialogFragment.REQUEST_KEY,
            viewLifecycleOwner
        ) { _, bundle ->
            val result = bundle.getInt(ThemeDialogFragment.KEY_RESPONSE)
            dialogFragment.dismiss()
            settingViewModel.editThemeDayNight(result)
            AppCompatDelegate.setDefaultNightMode(result)
        }
    }
}