package com.example.madplayground.ui.settings.source

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.madplayground.domain.logs.models.Logs
import com.example.madplayground.domain.messages.Message
import com.example.madplayground.domain.settings.usecases.*
import com.example.madplayground.ui.settings.models.SettingsScreen
import com.example.madplayground.ui.settings.models.SettingsScreen.State
import com.example.madplayground.ui.settings.models.SettingsScreen.ViewModel.Action
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class AndroidSettingsScreenViewModel @Inject constructor(
    logs: Logs,
    retrieveThemeTypeUseCase: RetrieveThemeTypeUseCase,
    retrieveIconographyTypeUseCase: RetrieveIconographyTypeUseCase,
    retrieveShapeTypeUseCase: RetrieveShapeTypeUseCase,
    retrieveNavigationLabelVisibilityUseCase: RetrieveNavigationLabelVisibilityUseCase,
    cycleThemeTypeUseCase: CycleThemeTypeUseCase,
    cycleIconographyTypeUseCase: CycleIconographyTypeUseCase,
    cycleShapeTypeUseCase: CycleShapeTypeUseCase,
    cycleNavigationLabelVisibilityUseCase: CycleNavigationLabelVisibilityUseCase,
) : ViewModel(), SettingsScreen.ViewModel {

    private val delegate = SettingsScreenViewModel(
        logs = logs,
        retrieveThemeTypeUseCase = retrieveThemeTypeUseCase,
        retrieveIconographyTypeUseCase = retrieveIconographyTypeUseCase,
        retrieveShapeTypeUseCase = retrieveShapeTypeUseCase,
        retrieveNavigationLabelVisibilityUseCase = retrieveNavigationLabelVisibilityUseCase,
        cycleThemeTypeUseCase = cycleThemeTypeUseCase,
        cycleIconographyTypeUseCase = cycleIconographyTypeUseCase,
        cycleShapeTypeUseCase = cycleShapeTypeUseCase,
        cycleNavigationLabelVisibilityUseCase = cycleNavigationLabelVisibilityUseCase,
        scope = viewModelScope
    )

    override val stateFlow: StateFlow<State> = delegate.stateFlow

    override val actionHandler: Message.Handler<Action> = delegate.actionHandler

}