package com.example.core.settings.source

import com.example.core.settings.models.Settings
import com.example.core.settings.repository.SettingsRepository
import com.example.core.settings.usecases.RetrieveShapeTypeUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class RetrieveShapeTypeUseCaseImpl(
    private val repository: SettingsRepository
) : RetrieveShapeTypeUseCase {

    override fun invoke(): Flow<Settings.ShapeType> {
        return repository.retrieveSettingsFlow().map { it.shapeType }
    }

}