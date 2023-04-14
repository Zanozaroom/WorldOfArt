package com.example.auth_inner.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import com.example.auth_inner.R
import com.example.auth_inner.ui.viewmodel.LoginViewModel.Companion.OnClickActions
import com.example.auth_inner.utill.AuthView
import com.example.auth_inner.utill.AuthView.Companion.ActionAuthView
import kotlinx.coroutines.flow.collectLatest

class SingInFragment : BaseAuthFragment(R.layout.fragment_sing_in) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewAuth = view.findViewById<AuthView>(R.id.authViewSingIn)
        viewAuth.setTextHeader(resources.getString(R.string.textAuthorisation))
        viewAuth.setIsHaveAccount(resources.getString(R.string.textNotAccount))
        viewAuth.setActionMainButton(resources.getString(R.string.textGoInApp))
        viewAuth.setTextProgress(resources.getString(R.string.textCheckAccount))

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
        viewAuth.setListenerActionsPageArt{
            when(it){
                ActionAuthView.ACTION_START ->
                    loginViewModel.onClickButton(
                        OnClickActions.TRY_LOGIN,
                        viewAuth.getEmailText(),
                        viewAuth.getPasswordText())

                ActionAuthView.IS_HAVE_ACCOUNT -> loginViewModel.onClickButton(
                    OnClickActions.NOT_ACCOUNT,
                    null,
                    null)
            }
        }
    }
}
