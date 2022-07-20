package com.example.madplayground.ui.logs

import androidx.compose.runtime.staticCompositionLocalOf
import com.example.madplayground.domain.logs.models.Logs

val LocalLogs = staticCompositionLocalOf<Logs> {
    Logs.EMPTY
}