package com.example.base_test

import com.example.project_alfa_angry_snake.App
import com.example.project_alfa_angry_snake.di.App.AppComponent

class AppTestBase: App() {
    override fun getComponent(): AppComponent{
        return DaggerAppComponentTestBase.factory()
            .create(this)
    }

}