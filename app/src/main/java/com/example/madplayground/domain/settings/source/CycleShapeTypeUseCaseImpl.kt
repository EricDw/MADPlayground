package com.example.madplayground.domain.settings.source

import com.example.madplayground.domain.settings.models.Settings
import com.example.madplayground.domain.settings.usecases.CycleShapeTypeUseCase
import com.example.madplayground.domain.settings.usecases.CycleThemeTypeUseCase
import com.example.madplayground.domain.settings.usecases.RetrieveThemeTypeUseCase

class CycleShapeTypeUseCaseImpl(
    private val settings: Settings,
) : CycleShapeTypeUseCase {

    override suspend fun invoke() {

        val newShapeType = when (
            settings.shapeType.value
        ) {

            Settings.ShapeType.ROUNDED -> {
                Settings.ShapeType.CUT
            }

            Settings.ShapeType.CUT     -> {
                Settings.ShapeType.ROUNDED
            }

        }

        return settings.setShapeType(newShapeType)
    }

}