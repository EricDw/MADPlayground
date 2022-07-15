package com.example.madplayground.ui.logs

import androidx.compose.runtime.Composable
import androidx.compose.runtime.staticCompositionLocalOf
import com.example.madplayground.logs.api.Logger

val LocalLogger = staticCompositionLocalOf<Logger> {
    Logger.EMPTY
}