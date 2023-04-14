package com.example.project_alfa_angry_snake.di.ui.activity

import android.app.Application
import android.content.Context

interface ActivityDepsProvider {
    val activityDeps: ActivityDeps
}
val Context.activityDeps: ActivityDeps
    get() = when(this){
        is ActivityDepsProvider -> this.activityDeps
        is Application -> error("Application must implements ActivityDeps")
        else -> this.applicationContext.activityDeps
    }
