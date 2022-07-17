package com.example.madplayground.ui.logs

import androidx.compose.runtime.staticCompositionLocalOf
import com.example.madplayground.features.logs.api.Logs

val LocalLogs = staticCompositionLocalOf<Logs> {
    Logs.EMPTY
}