package com.example.madplayground.ui.container.source

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.madplayground.common.logs.models.Logs
import com.example.madplayground.domain.settings.usecases.RetrieveIconographyTypeUseCase
import com.example.madplayground.domain.settings.usecases.RetrieveNavigationLabelVisibilityUseCase
import com.example.madplayground.domain.settings.usecases.RetrieveShapeTypeUseCase
import com.example.madplayground.domain.settings.usecases.RetrieveThemeTypeUseCase
import com.example.madplayground.ui.container.models.ContentContainer
import com.example.madplayground.ui.container.models.ContentContainer.State
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AndroidContentContainerViewModel @Inject constructor(
    logs: Logs,
    retrieveThemeTypeUseCase: RetrieveThemeTypeUseCase,
    retrieveIconographyTypeUseCase: RetrieveIconographyTypeUseCase,
    retrieveShapeTypeUseCase: RetrieveShapeTypeUseCase,
    retrieveNavigationLabelVisibilityUseCase: RetrieveNavigationLabelVisibilityUseCase,
) : ViewModel(), ContentContainer.ViewModel {

    private val delegate = ContentContainerViewModel(
        logs = logs,
        scope = viewModelScope,
        retrieveThemeTypeUseCase = retrieveThemeTypeUseCase,
        retrieveIconographyTypeUseCase = retrieveIconographyTypeUseCase,
        retrieveShapeTypeUseCase = retrieveShapeTypeUseCase,
        retrieveNavigationLabelVisibilityUseCase = retrieveNavigationLabelVisibilityUseCase,
    )

    override val state: State = delegate.state

    override val commandHandler = delegate.commandHandler

}