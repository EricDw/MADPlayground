package com.example.madplayground.ui.home.source

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.madplayground.domain.logs.models.Logs
import com.example.madplayground.domain.moments.usecases.RetrieveAllMomentUseCase
import com.example.madplayground.ui.screen.HomeScreen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class AndroidHomeScreenViewModel @Inject constructor(
    logs: Logs,
    retrieveAllMomentUseCase: RetrieveAllMomentUseCase,
) : ViewModel(), HomeScreen.ViewModel {

    private val delegate = HomeScreenViewModel(
        logs = logs,
        retrieveAllMomentUseCase = retrieveAllMomentUseCase,
        scope = viewModelScope
    )

    override val state: StateFlow<HomeScreen.State> =
        delegate.state

}