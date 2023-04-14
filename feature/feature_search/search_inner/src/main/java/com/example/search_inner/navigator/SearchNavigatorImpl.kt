package com.example.search_inner.navigator

import androidx.navigation.NavController
import com.example.navigation.DeepLinkDestination
import com.example.navigation.deepLinkNavigateTo
import javax.inject.Inject

class SearchNavigatorImpl @Inject constructor(): SearchNavigator {
    override var navController: NavController? = null

    override fun navigate(flow: SearchNavigatorFlow) {
       when(flow){
           SearchNavigatorFlow.ToBack -> {
               navController?.popBackStack() ?: throw IllegalArgumentException("You have to set navController")
           }
           is SearchNavigatorFlow.ToDeepLinkOpenPageOfArt -> navController?.deepLinkNavigateTo(
               DeepLinkDestination.PageOfArt(flow.msg)) ?: throw IllegalArgumentException("You have to set navController")
       }
    }
}