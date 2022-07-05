package com.example.madplayground.ui.app.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.madplayground.ui.app.api.App

class AppState : App.State {

    override var title: String by mutableStateOf("MAD Playground")

    override var isDarkTheme: Boolean by mutableStateOf(false)

}