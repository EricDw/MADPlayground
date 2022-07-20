package com.example.madplayground.domain.logs.models

/**
 * Handles the communication of log messages.
 */
interface Logs {

    /**
     * Logs that do nothing.
     */
    object EMPTY : Logs

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