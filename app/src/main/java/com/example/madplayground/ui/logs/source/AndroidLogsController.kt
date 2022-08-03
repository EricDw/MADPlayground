package com.example.madplayground.ui.logs.source

import android.util.Log

class AndroidLogsController : com.example.common.logs.models.Logs {

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