package com.example.project_alfa_angry_snake

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.fragment.NavHostFragment
import com.example.navigation.Navigator
import com.example.navigation.NavigatorI
import com.example.project_alfa_angry_snake.di.ui.activity.ActivComponent
import com.example.project_alfa_angry_snake.di.ui.activity.DaggerActivComponent
import com.example.project_alfa_angry_snake.di.ui.activity.activityDeps
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var navigator: NavigatorI
    var activityComponent: ActivComponent? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        installActivityComponentDagger()
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContentView(R.layout.activity_main)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainer) as NavHostFragment
        val navControllerActivity = navHostFragment.navController
        navigator.navController = navControllerActivity
    }

    private fun installActivityComponentDagger() {
        activityComponent = DaggerActivComponent
                .factory()
            .create(
              applicationContext.activityDeps,
                this@MainActivity)
        activityComponent!!.inject(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        activityComponent = null
        navigator.navController = null
    }
}
