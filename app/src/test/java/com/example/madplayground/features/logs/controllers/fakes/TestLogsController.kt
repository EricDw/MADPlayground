package com.example.madplayground.features.logs.controllers.fakes

import com.example.madplayground.features.logs.api.Logs

class TestLogsController : Logs {

    override fun logDebug(message: String, tag: String?, error: Throwable?) {
        println(
            "$tag:  $message${error?.toString() ?: ""}"
        )
    }

    override fun logError(message: String, tag: String?, error: Throwable?) {
        println(
            "$tag:  $message ${error?.toString() ?: ""}"
        )
    }

}