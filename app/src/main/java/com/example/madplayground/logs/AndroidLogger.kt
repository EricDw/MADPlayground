package com.example.madplayground.logs

import android.util.Log
import com.example.madplayground.logs.api.Logger

class AndroidLogger : Logger {

    override fun logDebug(message: String, tag: String?, error: Throwable?) {
        Log.d(tag, message, error)
    }

    override fun logError(message: String, tag: String?, error: Throwable?) {
        Log.e(tag, message, error)
    }

}