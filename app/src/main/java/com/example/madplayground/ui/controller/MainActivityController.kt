package com.example.madplayground.ui.controller

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowHeightSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import com.example.madplayground.domain.logs.models.Logs
import com.example.madplayground.ui.config.LocalWindowConfiguration
import com.example.madplayground.ui.config.WindowConfiguration
import com.example.madplayground.ui.config.WindowHeightType
import com.example.madplayground.ui.config.WindowWidthType
import com.example.madplayground.ui.container.screen.controller.ContentContainerController
import com.example.madplayground.ui.logs.LocalLogs
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivityController : ComponentActivity() {

    @Inject
    lateinit var logs: Logs

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            CompositionLocalProvider(
                LocalWindowConfiguration provides calculateWindowConfiguration(),
                LocalLogs provides logs,
            ) {

                ContentContainerController()

            }
        }

    }

    @Composable
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    private fun calculateWindowConfiguration(): WindowConfiguration {

        val windowSizeClass = calculateWindowSizeClass(activity = this)

        val width: WindowWidthType = when (windowSizeClass.widthSizeClass) {
            WindowWidthSizeClass.Compact -> WindowWidthType.COMPACT
            WindowWidthSizeClass.Medium  -> WindowWidthType.MEDIUM
            else                         -> WindowWidthType.EXPANDED
        }

        val height: WindowHeightType = when (windowSizeClass.heightSizeClass) {
            WindowHeightSizeClass.Compact -> WindowHeightType.COMPACT
            WindowHeightSizeClass.Medium  -> WindowHeightType.MEDIUM
            else                          -> WindowHeightType.EXPANDED
        }

        return WindowConfiguration(width, height)
    }

}