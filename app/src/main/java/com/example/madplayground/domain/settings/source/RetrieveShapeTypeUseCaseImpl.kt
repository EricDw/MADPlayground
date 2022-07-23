package com.example.madplayground.domain.settings.source

import com.example.madplayground.domain.settings.models.Settings
import com.example.madplayground.domain.settings.usecases.RetrieveShapeTypeUseCase
import com.example.madplayground.domain.settings.usecases.RetrieveThemeTypeUseCase
import kotlinx.coroutines.flow.Flow

class RetrieveShapeTypeUseCaseImpl(
    private val settings: Settings
) : RetrieveShapeTypeUseCase {

    override fun invoke(): Flow<Settings.ShapeType> {
        return settings.shapeType
    }

}