package com.example.madplayground.logs.api

interface Logger {

    fun logDebug(
        message: String,
        tag: String? = null,
        error: Throwable? = null,
    )

    fun logError(
        message: String,
        tag: String? = null,
        error: Throwable? = null,
    )

}