package com.example.madplayground.features.settings.ui.screens.viewmodel

import com.example.madplayground.features.app.apis.App
import com.example.madplayground.features.app.controllers.ApplicationController
import com.example.madplayground.features.settings.controllers.fakes.FakeSettingsController
import com.example.madplayground.features.settings.ui.screens.api.SettingsScreen
import com.example.madplayground.features.logs.controllers.fakes.TestLogsController
import com.example.madplayground.features.quotes.controllers.QuotesController
import com.example.madplayground.features.settings.apis.Settings
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain

import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class SettingsScreenViewModelTest {

    private lateinit var viewModel: SettingsScreen.ViewModel

    @Before
    fun setUp() {

        Dispatchers.setMain(UnconfinedTestDispatcher())

        val logs = TestLogsController()

        val settings = FakeSettingsController()

        val quotes = QuotesController()

        val app: App = ApplicationController(
            logs = logs,
            settings = settings,
            quotes = quotes
        )

        viewModel = SettingsScreenViewModel(
            app = app
        )

    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `given default settings when getting themeType then it is correct`() = runTest {

        // Arrange
        val expected = Settings.ThemeType.SYSTEM

        // Act
        val actual = viewModel.stateFlow.value.themeType.value

        // Assert
        val message = "The default themeType is not correct"

        Assert.assertEquals(
            message,
            expected,
            actual
        )

    }

    @Test
    fun `given default settings when getting shapeType then it is correct`() = runTest {

        // Arrange
        val expected = Settings.ShapeType.ROUNDED

        // Act
        val actual = viewModel.stateFlow.value.shapeType.value

        // Assert
        val message = "The default shapeType is not correct"

        Assert.assertEquals(
            message,
            expected,
            actual
        )

    }

    @Test
    fun `given default settings when getting iconType then it is correct`() = runTest {

        // Arrange
        val expected = Settings.IconographyType.DEFAULT

        // Act
        val actual = viewModel.stateFlow.value.iconType.value

        // Assert
        val message = "The default iconType is not correct"

        Assert.assertEquals(
            message,
            expected,
            actual
        )

    }

    @Test
    fun `given default settings when getting NavigationLabelVisibility then it is correct`() = runTest {

        // Arrange
        val expected = Settings.NavigationLabelVisibility.WHEN_SELECTED

        // Act
        val actual = viewModel.stateFlow.value.navigationLabelVisibility.value

        // Assert
        val message = "The default navigationLabelVisibility is not correct"

        Assert.assertEquals(
            message,
            expected,
            actual
        )

    }

    @Test
    fun `given default settings when CycleLabelVisibility and checking the state then it has changed`() = runTest {

        // Arrange
        val expected = Settings.NavigationLabelVisibility.NEVER
        val input = SettingsScreen.ViewModel.Action.CycleLabelVisibility

        // Act
        viewModel.actionHandler(message = input)
        val actual = viewModel.stateFlow.value.navigationLabelVisibility.value

        // Assert
        val message = "The NavigationLabelVisibility did not update to the correct value."

        Assert.assertEquals(
            message,
            expected,
            actual
        )

    }

    @Test
    fun `given default settings when CycleThemeType and checking the state then it has changed`() = runTest {

        // Arrange
        val expected = Settings.ThemeType.LIGHT
        val input = SettingsScreen.ViewModel.Action.CycleThemeType

        // Act
        viewModel.actionHandler(message = input)
        val actual = viewModel.stateFlow.value.themeType.value

        // Assert
        val message = "The ThemeType did not update to the correct value."

        Assert.assertEquals(
            message,
            expected,
            actual
        )

    }

}