package com.example.core.settings.source

import com.example.core.settings.models.Settings
import com.example.core.settings.repository.SettingsCache
import com.example.core.settings.usecases.CycleShapeTypeUseCase

class CycleShapeTypeUseCaseImpl(
    private val cache: SettingsCache,
) : CycleShapeTypeUseCase {

    override suspend fun invoke() {

        val newShapeType = when (
            cache.shapeType.value
        ) {

            Settings.ShapeType.ROUNDED -> {
                Settings.ShapeType.CUT
            }

            Settings.ShapeType.CUT     -> {
                Settings.ShapeType.ROUNDED
            }

        }

        return cache.setShapeType(newShapeType)
    }

}