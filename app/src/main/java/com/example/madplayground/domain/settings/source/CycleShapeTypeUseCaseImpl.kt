package com.example.madplayground.domain.settings.source

import com.example.madplayground.cache.settings.models.SettingsCache
import com.example.madplayground.domain.settings.models.Settings
import com.example.madplayground.domain.settings.usecases.CycleShapeTypeUseCase

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