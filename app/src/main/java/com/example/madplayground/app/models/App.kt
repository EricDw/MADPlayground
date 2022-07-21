package com.example.madplayground.app.models

import com.example.madplayground.domain.logs.models.Logs
import com.example.madplayground.domain.moments.models.Moments
import com.example.madplayground.domain.settings.models.Settings

interface App {

    val logs: Logs

    val settings: Settings

    val moments: Moments

}