package com.example.madplayground.features.app.apis

import com.example.madplayground.features.logs.api.Logs
import com.example.madplayground.features.quotes.apis.Quotes
import com.example.madplayground.features.settings.apis.Settings

interface App {

    val logs: Logs

    val settings: Settings

    val quotes: Quotes

}