package com.example.core_api.utills

import android.content.Context
import android.view.KeyEvent
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.core.widget.doOnTextChanged
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.onStart


@OptIn(FlowPreview::class)
fun EditText.textChanges(): Flow<CharSequence?> {
    return callbackFlow {
        val listener = doOnTextChanged { text, _, _, _ -> trySend(text) }
        awaitClose { removeTextChangedListener(listener) }
    }.debounce(500)
        .onStart { emit(text) }
}

inline fun EditText.hideKeyAndStartAction(crossinline action: () -> Unit) {
    this.setOnKeyListener { view, keyCode, event ->
        if ((event?.action == KeyEvent.ACTION_DOWN) &&
            (keyCode == KeyEvent.KEYCODE_ENTER)
        ) {
            val manager = view.context
                .getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            manager.hideSoftInputFromWindow(this.windowToken, 0)
            action()
            return@setOnKeyListener true
        } else return@setOnKeyListener false
    }
}

fun EditText.hideKeyWithoutAction() {
    this.setOnKeyListener { view, keyCode, event ->
        if ((event?.action == KeyEvent.ACTION_DOWN) &&
            (keyCode == KeyEvent.KEYCODE_ENTER)
        ) {
            val manager = view.context
                .getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            manager.hideSoftInputFromWindow(this.windowToken, 0)
            return@setOnKeyListener true
        } else return@setOnKeyListener false
    }
    val manager = context
        .getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    manager.hideSoftInputFromWindow(this.windowToken, 0)
}

