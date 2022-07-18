package com.example.madplayground.features.app.controllers

import android.app.Application
import com.example.madplayground.features.app.apis.App
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class AndroidApplicationController: Application() {

    private val tag = this::class.simpleName

    @Inject lateinit var app: App

    override fun onCreate() {
        // Variables are injected after super
        super.onCreate()

        app.logs.logDebug(
            tag = tag,
            message = "Created"
        )

    }
}