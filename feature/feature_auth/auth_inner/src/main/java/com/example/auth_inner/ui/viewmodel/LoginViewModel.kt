package com.example.auth_inner.ui.viewmodel

import android.util.Patterns
import androidx.lifecycle.viewModelScope
import com.example.api_model.ui.BaseViewModel
import com.example.auth_inner.R
import com.example.auth_inner.navigation.AuthNavigationFlow
import com.example.auth_inner.utill.*
import com.example.auth_inner.utill.AuthView.Companion.EditTextResult
import com.example.core_api.LoggerApp
import com.example.core_api.SharedPrefInterface
import com.example.navigation.NavigationBottomMenuFlow
import com.example.navigation.NavigationFlow
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

class LoginViewModel @Inject constructor(
    private val sharedPref: SharedPrefInterface,
    private val firebaseAuth: FirebaseAuth,
    private val loggerApp: LoggerApp
) : BaseViewModel() {

    override val eventNavigationFlow = Channel<NavigationFlow>()
    override val observerNavigationFlow = eventNavigationFlow.receiveAsFlow()
    private val eventAuthNavigationFlow = Channel<AuthNavigationFlow>()
    val observerAuthNavigationFlow = eventAuthNavigationFlow.receiveAsFlow()

    private val _stateViewModel = MutableStateFlow<ViewLoginState>(WaitUserDataState)
    val stateViewModel = _stateViewModel.asStateFlow()

    fun onClickButton(action: OnClickActions, email: String?, password: String?) {
        viewModelScope.launch {
            when (action) {
                OnClickActions.NOT_ACCOUNT -> 
                    eventAuthNavigationFlow.send(AuthNavigationFlow.ToRegister)
                OnClickActions.HAVE_ACCOUNT -> 
                    eventAuthNavigationFlow.send(AuthNavigationFlow.ToLogin)
                OnClickActions.TRY_REGISTER -> 
                    registerFireBase(email!!, password!!)
                OnClickActions.TRY_LOGIN -> 
                    loginFireBase(email!!, password!!)
            }
        }
    }

    fun onFlowTextListener(
        flowTextEmail: Flow<CharSequence?>,
        flowTextPassword: Flow<CharSequence?>
    ):Flow<EditTextResult> {
        return flowTextEmail.combine(flowTextPassword) { flowEmail, flowPassword ->
            val textEmail = flowEmail.toString().trim()
            val textPassword = flowPassword.toString().trim()
            when {
                textEmail.isBlank() || textPassword.isBlank() -> 
                    EditTextResult.EMPTY_FIELD
                textPassword.length < MIN_LENGTH_PASSWORD -> 
                    EditTextResult.ERR_PASSWORD_FIELD
                !Patterns.EMAIL_ADDRESS.matcher(textEmail).matches() ->
                    EditTextResult.ERR_EMAIL_FIELD
                else -> EditTextResult.ALL_RIGHT
            }
        }
    }

    private fun registerFireBase(email: String, password: String) {
        _stateViewModel.value = TryDoItState

        firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                viewModelScope.launch {
                    sharedPref.editIsLogin(true)
                    eventNavigationFlow.send(NavigationBottomMenuFlow)
                }
            }
            .addOnFailureListener {
                viewModelScope.launch {
                    loggerApp.logError(it.message.toString())
                    eventMessageFlow.send(R.string.errorSingUpText)
                    _stateViewModel.value = WaitUserDataState
                }
            }
    }

    private fun loginFireBase(email: String, password: String) {
        _stateViewModel.value = TryDoItState

        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                viewModelScope.launch {
                    sharedPref.editIsLogin(true)
                    eventNavigationFlow.send(NavigationBottomMenuFlow)
                }
            }
            .addOnFailureListener {
                viewModelScope.launch {
                    loggerApp.logError(it.message.toString())
                    eventMessageFlow.send(R.string.errorSingUpText)
                    _stateViewModel.value = WaitUserDataState
                }
            }
    }

    companion object {
        const val MIN_LENGTH_PASSWORD = 6
        enum class OnClickActions {
            NOT_ACCOUNT, HAVE_ACCOUNT, TRY_REGISTER, TRY_LOGIN
        }
    }
}

