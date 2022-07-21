package com.example.madplayground.app.source

import android.app.Application
import com.example.madplayground.domain.logs.models.Logs
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class AndroidApplicationController : Application() {

    private val tag = this::class.simpleName

    @Inject
    lateinit var logs: Logs

    override fun onCreate() {
        // Variables are injected after super
        super.onCreate()

        logs.logDebug(
            tag = tag,
            message = "Created"
        )

    }
}