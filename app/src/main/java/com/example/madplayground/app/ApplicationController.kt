package com.example.madplayground.app

import android.app.Application
import com.example.madplayground.models.SettingsController
import com.example.madplayground.models.apis.App
import com.example.madplayground.models.apis.Settings
import dagger.Provides
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject
import javax.inject.Singleton

@HiltAndroidApp
class ApplicationController @Inject constructor() : Application(), App {

    override val settings: Settings = SettingsController()

}