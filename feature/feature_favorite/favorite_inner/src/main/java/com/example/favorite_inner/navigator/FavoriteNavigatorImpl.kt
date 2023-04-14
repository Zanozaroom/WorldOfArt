package com.example.favorite_inner.navigator

import androidx.navigation.NavController
import com.example.navigation.DeepLinkDestination
import com.example.navigation.deepLinkNavigateTo
import javax.inject.Inject

class FavoriteNavigatorImpl @Inject constructor():FavoriteNavigator {
    override var navController: NavController? = null

    override fun navigate(favoriteNavigationFlow: FavoriteNavigationFlow) {
        when(favoriteNavigationFlow){
            is FavoriteNavigationFlow.ToDeepLinkPageOfArt -> {
                navController?.deepLinkNavigateTo(DeepLinkDestination.PageOfArt(favoriteNavigationFlow.msg))
                    ?: throw IllegalArgumentException ("You have to set navController")
            }
        }
    }


}