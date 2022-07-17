package com.example.madplayground.features.app.controllers

import com.example.madplayground.features.app.apis.App
import com.example.madplayground.features.logs.api.Logs
import com.example.madplayground.features.quotes.apis.Quotes
import com.example.madplayground.features.settings.apis.Settings
import javax.inject.Inject

class ApplicationController @Inject constructor (
    override val logs: Logs,
    override val settings: Settings,
    override val quotes: Quotes,
) : App