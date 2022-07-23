package com.example.madplayground.ui.settings.models

import com.example.madplayground.domain.messages.Message
import com.example.madplayground.domain.settings.models.Settings
import kotlinx.coroutines.flow.StateFlow

interface SettingsScreen {

    companion object {
        const val ROUTE: String = "settings"
    }

    sealed interface Event : Message {

        object ThemeTypeClicked : Event

        object IconTypeClicked : Event

        object ShapeTypeClicked : Event

        object LabelVisibilityClicked : Event

    }

    interface State {

        val themeType: StateFlow<Settings.ThemeType>

        val iconType: StateFlow<Settings.IconographyType>

        val shapeType: StateFlow<Settings.ShapeType>

        val navigationLabelVisibility: StateFlow<Settings.NavigationLabelVisibility>

    }

    interface ViewModel {

        val stateFlow: StateFlow<State>

        val actionHandler: Message.Handler<Action>

        sealed interface Action : Message {

            object CycleThemeType : Action {
                override fun toString(): String {
                    return this::class.simpleName ?: super.toString()
                }
            }

            object CycleIconographyType : Action {
                override fun toString(): String {
                    return this::class.simpleName ?: super.toString()
                }
            }

            object CycleShapeType : Action {
                override fun toString(): String {
                    return this::class.simpleName ?: super.toString()
                }
            }

            object CycleLabelVisibility : Action {
                override fun toString(): String {
                    return this::class.simpleName ?: super.toString()
                }
            }

        }

    }

}