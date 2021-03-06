package com.example.madplayground.app.controller

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class AndroidApplicationController : Application() {

    private val tag = this::class.simpleName

    @Inject
    lateinit var logs: com.example.common.logs.models.Logs

    override fun onCreate() {
        super.onCreate() // Injection happens here

        logs.logDebug(
            tag = tag,
            message = "Created"
        )

    }

}