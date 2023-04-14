package com.example.setting_inner.ui.fragment

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import com.example.setting_inner.R
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class ThemeDialogFragment : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val setTheme = arrayOf(
            resources.getString(R.string.textLightTheme),
            resources.getString(R.string.textDarkTheme),
            resources.getString(R.string.textMobileTheme)
        )
        return MaterialAlertDialogBuilder(requireContext())
            .setTitle(R.string.headerDialogSetTheme)
            .setItems(setTheme) { dialog, which ->
                parentFragmentManager.setFragmentResult(
                    REQUEST_KEY,
                    bundleOf(KEY_RESPONSE to hashMap[which])
                )
                dialog.dismiss()
            }
            .create()
    }

    companion object {
        const val TAG = "SetThemeDialogFragment"
        val REQUEST_KEY = "$TAG:defaultrequestkey"
        val KEY_RESPONSE = "$TAG:Response"
        private const val DAY_THEME = 0
        private const val NIGHT_THEME = 1
        private const val SYSTEM_THEME = 2
        private val hashMap = hashMapOf<Int, Int>(
            DAY_THEME to AppCompatDelegate.MODE_NIGHT_NO,
            NIGHT_THEME to AppCompatDelegate.MODE_NIGHT_YES,
            SYSTEM_THEME to AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
        )
    }

}