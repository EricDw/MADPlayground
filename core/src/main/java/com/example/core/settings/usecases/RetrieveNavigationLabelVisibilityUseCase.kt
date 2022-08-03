package com.example.core.settings.usecases

import com.example.core.settings.models.Settings
import kotlinx.coroutines.flow.Flow

fun interface RetrieveNavigationLabelVisibilityUseCase {

    operator fun invoke(): Flow<Settings.NavigationLabelVisibility>

}