package com.example.project_alfa_angry_snake.di.ui.activity

import com.example.navigation.NavigatorI
import com.example.project_alfa_angry_snake.MainActivity
import com.example.project_alfa_angry_snake.fragment.StartFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Qualifier

@ActivityScope
@Component(dependencies = [ActivityDeps::class], modules = [ActivityVMFactory::class])
interface ActivComponent {
    fun inject(activity: MainActivity)
    fun inject(fragment: StartFragment)

    @Component.Factory
    interface Factory{
        fun create(
            appComponent: ActivityDeps,
            @BindsInstance activity: MainActivity
        ): ActivComponent
    }
}


@Qualifier
annotation class ActivityScope
