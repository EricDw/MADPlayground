package com.example.madplayground.ui.logs.source

import androidx.compose.runtime.staticCompositionLocalOf
import com.example.madplayground.common.logs.models.Logs

val LocalLogs = staticCompositionLocalOf<Logs> {
    Logs.EMPTY
}