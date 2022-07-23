package com.example.madplayground.domain.settings.usecases

import com.example.madplayground.domain.settings.models.Settings
import kotlinx.coroutines.flow.Flow

fun interface RetrieveThemeTypeUseCase {

    operator fun invoke(): Flow<Settings.ThemeType>

}