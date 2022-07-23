package com.example.madplayground.ui.moments.models

import kotlinx.coroutines.flow.StateFlow

interface MomentFormUiState {

        val description: StateFlow<String>

        val date: StateFlow<String>

        val time: StateFlow<String>

        val submitting: StateFlow<Boolean>

        val submitted: StateFlow<Boolean>

    }
