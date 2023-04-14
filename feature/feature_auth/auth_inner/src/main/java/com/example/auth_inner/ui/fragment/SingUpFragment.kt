package com.example.auth_inner.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import com.example.auth_inner.R
import com.example.auth_inner.ui.viewmodel.LoginViewModel.Companion.OnClickActions
import com.example.auth_inner.utill.AuthView
import com.example.auth_inner.utill.AuthView.Companion.ActionAuthView
import kotlinx.coroutines.flow.collectLatest

class SingUpFragment: BaseAuthFragment(R.layout.fragment_sing_up) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewAuth = view.findViewById<AuthView>(R.id.authViewSingUp)

        viewAuth.setTextHeader(resources.getString(R.string.textRegistration))
        viewAuth.setIsHaveAccount(resources.getString(R.string.textHaveAccount))
        viewAuth.setActionMainButton(resources.getString(R.string.textRegistration))
        viewAuth.setTextProgress(resources.getString(R.string.textCreateAccount))

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            loginViewModel.stateViewModel.collect {
                viewAuth.setViewLoginState(it)
            }
        }
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            loginViewModel.onFlowTextListener(viewAuth.getFlowEmail(), viewAuth.getFlowPassword()).collectLatest {
                viewAuth.setResultTextChange(it)
            }
        }

        viewAuth.setListenerActionsPageArt(){
            when(it){
                ActionAuthView.ACTION_START ->
                    loginViewModel.onClickButton(
                        OnClickActions.TRY_REGISTER,
                        viewAuth.getEmailText(),
                        viewAuth.getPasswordText())
                ActionAuthView.IS_HAVE_ACCOUNT -> loginViewModel.onClickButton(
                    OnClickActions.HAVE_ACCOUNT,
                    null,
                    null)
            }
        }
    }
}
