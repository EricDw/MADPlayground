package com.example.madplayground.logs.api

/**
 * Handles the communication of log messages.
 */
interface Logger {

    /**
     * Logger that does nothing.
     */
    object EMPTY : Logger

    /**
     * Log a Debug message.
     */
    fun logDebug(
        message: String,
        tag: String? = null,
        error: Throwable? = null,
    ) {
        /* no-op */
    }

    /**
     * Log an Error message.
     */
    fun logError(
        message: String,
        tag: String? = null,
        error: Throwable? = null,
    ) {
        /* no-op */
    }

}