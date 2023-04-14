package com.example.auth_inner.utills


import android.app.Application
import android.content.Context
import androidx.test.runner.AndroidJUnitRunner
import com.example.base_test.AppTestBase

class Runner : AndroidJUnitRunner() {
    override fun newApplication(
        cl: ClassLoader?,
        className: String?,
        context: Context?
    ): Application {
        return super.newApplication(cl, AppTestBase::class.java.name, context)
    }
}