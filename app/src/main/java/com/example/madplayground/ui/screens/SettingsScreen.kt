package com.example.madplayground.ui.screens

import com.example.madplayground.domain.messages.Message
import com.example.madplayground.domain.settings.models.Settings
import kotlinx.coroutines.flow.StateFlow

interface SettingsScreen: Screen {

    fun onEvent(event: Event)

    sealed interface Event : Message {

        object BackClicked : Event

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

        val state: State

        val commandHandler: Message.Handler<Command>

        sealed interface Command : Message {

            object CycleThemeType : Command {
                override fun toString(): String {
                    return this::class.simpleName ?: super.toString()
                }
            }

            object CycleIconographyType : Command {
                override fun toString(): String {
                    return this::class.simpleName ?: super.toString()
                }
            }

            object CycleShapeType : Command {
                override fun toString(): String {
                    return this::class.simpleName ?: super.toString()
                }
            }

            object CycleLabelVisibility : Command {
                override fun toString(): String {
                    return this::class.simpleName ?: super.toString()
                }
            }

        }

    }

    companion object {
        const val ROUTE: String = "settings"
    }

}