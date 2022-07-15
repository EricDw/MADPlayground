package com.example.madplayground.ui.container.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.madplayground.logs.api.Logger
import com.example.madplayground.messages.api.Message
import com.example.madplayground.models.apis.App
import com.example.madplayground.ui.config.CombinedWindowType
import com.example.madplayground.ui.container.ContentContainerState
import com.example.madplayground.ui.container.api.ContentContainer
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class ContentContainerViewModel @Inject constructor(
    private val logger: Logger,
    private val app: App,
) : ViewModel(), ContentContainer.ViewModel {

    private val tag = this::class.simpleName

    private var state = ContentContainerState()

    private val _stateFlow: MutableStateFlow<ContentContainer.State> =
        MutableStateFlow(state)

    private val _sideEffect = MutableStateFlow<ContentContainer.SideEffect?>(null)

    override val stateFlow: StateFlow<ContentContainer.State> =
        _stateFlow.asStateFlow()

    override val sideEffect: StateFlow<ContentContainer.SideEffect?> =
        _sideEffect.asStateFlow()

    private var themeSynchronizer: Job? = app.settings.themeType
        .onEach { themeType ->

            state.themeType = themeType

        }.launchIn(viewModelScope)

    private var iconSynchronizer: Job? = app.settings.iconographyType
        .onEach { iconographyType ->

            state.iconographyType = iconographyType

        }.launchIn(viewModelScope)

    private var shapeSynchronizer: Job? = app.settings.shapeType
        .onEach { shapeType ->

            state.shapeType = shapeType

        }.launchIn(viewModelScope)

    override val eventHandler: Message.Handler<ContentContainer.Event> =
        Message.Handler { event ->
            when (event) {

                ContentContainer.Event.HomeTabClicked                -> {
                    logger.logDebug(
                        tag = tag,
                        message = "Handling event: ${event::class.simpleName}"
                    )

                    _sideEffect.value = ContentContainer.SideEffect.NavigateToHomeTab {
                        _sideEffect.value = null
                    }

                }

                ContentContainer.Event.SettingsTabClicked            -> {

                    logger.logDebug(
                        tag = tag,
                        message = "Handling event: ${event::class.simpleName}"
                    )

                    _sideEffect.value = ContentContainer.SideEffect.NavigateToSettingsTab {
                        _sideEffect.value = null
                    }

                }

                ContentContainer.Event.NavigationButtonClicked       -> {

                    logger.logDebug(
                        tag = tag,
                        message = "Handling event: ${event::class.simpleName}"
                    )

                    // TODO: Use strategy pattern here

                    when (stateFlow.value.screenContext) {

                        ContentContainer.ScreenContext.HOME     -> {
                            state.triggerDrawerToOpen = true
                        }

                        ContentContainer.ScreenContext.SETTINGS -> {

                            logger.logDebug(
                                tag = tag,
                                message = "Navigating Back"
                            )

                            _sideEffect.value = ContentContainer.SideEffect.NavigateBack {
                                _sideEffect.value = null
                            }

                        }

                    }

                }

                ContentContainer.Event.HomeScreenStarted             -> {
                    state.screenContext = ContentContainer.ScreenContext.HOME
                }

                ContentContainer.Event.SettingsScreenStarted         -> {
                    state.screenContext = ContentContainer.ScreenContext.SETTINGS
                }

                ContentContainer.Event.DrawerClosed                  -> {
                    state.triggerDrawerToOpen = false

                    logger.logDebug(
                        tag = tag,
                        message = "Drawer closed"
                    )
                }

                is ContentContainer.Event.WindowConfigurationChanged -> {

                    val windowConfiguration = event.newWindowConfiguration

                    when (
                        windowConfiguration.combinedWindowType
                    ) {

                        CombinedWindowType.COMPACT_WIDTH_COMPACT_HEIGHT   -> {
                            // TODO: Configure State

                            logger.logDebug(
                                tag = tag,
                                message = "$windowConfiguration not handled!"
                            )

                        }

                        CombinedWindowType.COMPACT_WIDTH_MEDIUM_HEIGHT    -> {

                            with(state) {

                                showTopAppBar = true

                                showBottomNavBar = true

                                showNavigationRail = false

                            }

                            logger.logDebug(
                                tag = tag,
                                message = "$windowConfiguration not handled!"
                            )

                        }

                        CombinedWindowType.COMPACT_WIDTH_EXPANDED_HEIGHT  -> {
                            // TODO: Configure State

                            logger.logDebug(
                                tag = tag,
                                message = "$windowConfiguration not handled!"
                            )

                        }

                        CombinedWindowType.MEDIUM_WIDTH_COMPACT_HEIGHT    -> {
                            // TODO: Configure State

                            with(state) {

                                showTopAppBar = false

                                showBottomNavBar = false

                                showNavigationRail = true

                            }

                            logger.logDebug(
                                tag = tag,
                                message = "$windowConfiguration not handled!"
                            )

                        }

                        CombinedWindowType.MEDIUM_WIDTH_MEDIUM_HEIGHT     -> {
                            // TODO: Configure State

                            logger.logDebug(
                                tag = tag,
                                message = "$windowConfiguration not handled!"
                            )

                        }

                        CombinedWindowType.MEDIUM_WIDTH_EXPANDED_HEIGHT   -> {
                            // TODO: Configure State

                            logger.logDebug(
                                tag = tag,
                                message = "$windowConfiguration not handled!"
                            )

                        }

                        CombinedWindowType.EXPANDED_WIDTH_COMPACT_HEIGHT  -> {
                            // TODO: Configure State

                            logger.logDebug(
                                tag = tag,
                                message = "$windowConfiguration not handled!"
                            )

                        }

                        CombinedWindowType.EXPANDED_WIDTH_MEDIUM_HEIGHT   -> {
                            // TODO: Configure State

                            logger.logDebug(
                                tag = tag,
                                message = "$windowConfiguration not handled!"
                            )

                        }

                        CombinedWindowType.EXPANDED_WIDTH_EXPANDED_HEIGHT -> {
                            // TODO: Configure State

                            logger.logDebug(
                                tag = tag,
                                message = "$windowConfiguration not handled!"
                            )

                        }

                    }

                }

            }
        }

    override fun onCleared() {
        super.onCleared()
        themeSynchronizer?.cancel()
        themeSynchronizer = null

        iconSynchronizer?.cancel()
        iconSynchronizer = null

        shapeSynchronizer?.cancel()
        shapeSynchronizer = null

    }

}