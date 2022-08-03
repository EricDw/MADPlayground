package com.example.core.settings.source

import com.example.core.settings.models.Settings
import com.example.core.settings.repository.SettingsCache
import com.example.core.settings.usecases.RetrieveShapeTypeUseCase
import kotlinx.coroutines.flow.Flow

class RetrieveShapeTypeUseCaseImpl(
    private val cache: SettingsCache
) : RetrieveShapeTypeUseCase {

    override fun invoke(): Flow<Settings.ShapeType> {
        return cache.shapeType
    }

}