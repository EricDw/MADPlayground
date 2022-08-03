package com.example.core.settings.usecases

import com.example.core.settings.models.Settings
import kotlinx.coroutines.flow.Flow

fun interface RetrieveShapeTypeUseCase {

    operator fun invoke(): Flow<Settings.ShapeType>

}