package com.example.auth_inner.navigation

import androidx.navigation.NavController
import com.example.auth_inner.R
import javax.inject.Inject

class NavigatorAuthImpl @Inject constructor():NavigatorAuth{
    override var navController: NavController? = null
    override fun navigate(flow: AuthNavigationFlow) {
     return when (flow){
         AuthNavigationFlow.ToLogin -> navController?.navigate(R.id.action_singUpFragment_to_singInFragment)
             ?: throw IllegalArgumentException("You have to set navController")
         AuthNavigationFlow.ToRegister ->navController?.navigate(R.id.action_singInFragment_to_singUpFragment)
             ?: throw IllegalArgumentException("You have to set navController")
     }
    }
}