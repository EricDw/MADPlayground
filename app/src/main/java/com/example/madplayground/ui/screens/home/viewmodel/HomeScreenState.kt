package com.example.madplayground.ui.screens.home.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import com.example.madplayground.ui.screens.home.api.HomeScreen

class HomeScreenState : HomeScreen.State {

    override val title: String by mutableStateOf("Home Screen")

}