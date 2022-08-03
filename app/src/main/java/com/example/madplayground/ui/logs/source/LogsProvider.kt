package com.example.madplayground.ui.logs.source

import androidx.compose.runtime.staticCompositionLocalOf

val LocalLogs = staticCompositionLocalOf<com.example.common.logs.models.Logs> {
    com.example.common.logs.models.Logs.EMPTY
}