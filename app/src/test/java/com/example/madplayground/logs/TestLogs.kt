package com.example.madplayground.logs

import com.example.madplayground.common.logs.models.Logs

class TestLogs : Logs {

    override fun logDebug(message: String, tag: String?, error: Throwable?) {
        println(
            "$tag: $message"
        )
    }

    override fun logError(message: String, tag: String?, error: Throwable?) {
        println(
            "$tag: $message $error"
        )
    }
}