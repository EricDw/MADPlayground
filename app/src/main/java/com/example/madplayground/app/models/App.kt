package com.example.madplayground.app.models

import com.example.madplayground.domain.logs.models.Logs
import com.example.madplayground.domain.quotes.models.Quotes
import com.example.madplayground.domain.settings.models.Settings

interface App {

    val logs: Logs

    val settings: Settings

    val quotes: Quotes

}