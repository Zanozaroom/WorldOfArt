package com.example.museumart_inner.navigation

import androidx.navigation.NavController
import com.example.museumart_inner.ui.fragment.MuseumArtWorkFragmentDirections
import javax.inject.Inject


class NavigatorMuseumImpl @Inject constructor(): NavigatorMuseum {
    override var navController: NavController? = null
    override fun navFlowMuseum(flow: FlowMuseumNav){
        when(flow){
            is FlowMuseumNav.ToArtPage -> {
                val action = MuseumArtWorkFragmentDirections.actionFragmentMuseumListToPageArtWorkFragment(flow.msg)
                navController?.navigate(action) ?: throw IllegalArgumentException ("You have to set navController")
            }
            is FlowMuseumNav.ToSearch -> {
                val action = MuseumArtWorkFragmentDirections.actionSearchFlow()
                navController?.navigate(action) ?: throw IllegalArgumentException ("You have to set navController")
            }
        }
    }
}

sealed class FlowMuseumNav{
    data class ToArtPage(val msg: String): FlowMuseumNav()
    object ToSearch: FlowMuseumNav()
}