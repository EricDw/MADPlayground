package com.example.madplayground.ui.logs.source

import android.util.Log
import com.example.madplayground.common.logs.models.Logs

class AndroidLogsController : Logs {

    override fun logDebug(
        message: String,
        tag: String?,
        error: Throwable?,
    ) {
        Log.d(tag, message, error)
    }

    override fun logError(
        message: String,
        tag: String?,
        error: Throwable?,
    ) {
        Log.e(tag, message, error)
    }

}