package com.example.madplayground.domain.settings.source

import com.example.madplayground.cache.settings.models.SettingsCache
import com.example.madplayground.domain.settings.models.Settings
import com.example.madplayground.domain.settings.usecases.RetrieveShapeTypeUseCase
import kotlinx.coroutines.flow.Flow

class RetrieveShapeTypeUseCaseImpl(
    private val cache: SettingsCache
) : RetrieveShapeTypeUseCase {

    override fun invoke(): Flow<Settings.ShapeType> {
        return cache.shapeType
    }

}