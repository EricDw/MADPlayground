package com.example.madplayground.logs

class TestLogs : com.example.common.logs.models.Logs {

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