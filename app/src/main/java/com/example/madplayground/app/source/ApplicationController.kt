package com.example.madplayground.app.source

import com.example.madplayground.app.models.App
import com.example.madplayground.domain.logs.models.Logs
import com.example.madplayground.domain.quotes.models.Quotes
import com.example.madplayground.domain.settings.models.Settings
import javax.inject.Inject

class ApplicationController @Inject constructor (
    override val logs: Logs,
    override val settings: Settings,
    override val quotes: Quotes,
) : App