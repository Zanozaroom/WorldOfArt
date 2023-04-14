package com.example.core_inner.logger

import android.util.Log
import com.example.core_api.LoggerApp
import javax.inject.Inject

class LoggerAppImpl @Inject constructor():LoggerApp {
    override fun logInfo(msg: String) {
        Log.i(INFO_APP_TAG, msg)
    }

    override fun logError(msg: String) {
        Log.i(ERROR_APP_TAG, msg)
    }

    companion object{
        const val ERROR_APP_TAG = "ERROR APP"
        const val INFO_APP_TAG = "INFO APP"
    }
}