package com.example.madplayground.ui.container.source

import com.example.madplayground.cache.settings.fakes.FakeSettingsCache
import com.example.madplayground.cache.settings.models.SettingsCache
import com.example.madplayground.common.logs.models.Logs
import com.example.madplayground.domain.settings.models.Settings
import com.example.madplayground.domain.settings.source.RetrieveIconographyTypeUseCaseImpl
import com.example.madplayground.domain.settings.source.RetrieveNavigationLabelVisibilityUseCaseImpl
import com.example.madplayground.domain.settings.source.RetrieveShapeTypeUseCaseImpl
import com.example.madplayground.domain.settings.source.RetrieveThemeTypeUseCaseImpl
import com.example.madplayground.logs.TestLogs
import com.example.madplayground.ui.container.models.ContentContainer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class TheContentContainerViewModel {

    private lateinit var cache: SettingsCache

    private lateinit var viewModel: ContentContainer.ViewModel

    private lateinit var logs: Logs

    private lateinit var dispatcher: TestDispatcher

    @Before
    fun setUp() {

        dispatcher = UnconfinedTestDispatcher()

        Dispatchers.setMain(dispatcher)

        logs = TestLogs()

        cache = FakeSettingsCache(
            logs =  logs
        )

        viewModel = ContentContainerViewModel(
            logs = logs,
            retrieveThemeTypeUseCase = RetrieveThemeTypeUseCaseImpl(
                cache = cache,
            ),
            retrieveIconographyTypeUseCase = RetrieveIconographyTypeUseCaseImpl(
                cache = cache
            ),
            retrieveShapeTypeUseCase = RetrieveShapeTypeUseCaseImpl(
                cache = cache
            ),
            retrieveNavigationLabelVisibilityUseCase = RetrieveNavigationLabelVisibilityUseCaseImpl(
                cache = cache
            ),
            scope = CoroutineScope(dispatcher)
        )

    }

    @After
    fun tearDown() {

        Dispatchers.resetMain()

    }

    @Test
    fun `Loads the ThemeType upon initialization`() {
        // Arrange
        val expected = Settings.ThemeType.SYSTEM

        // Act
        val actual = viewModel.stateFlow.value.themeType

        // Assert
        Assert.assertEquals(
            /* message = */ "ThemeType not loaded",
            /* expected = */ expected,
            /* actual = */ actual
        )
    }

    @Test
    fun `Loads the IconographyType upon initialization`() {
        // Arrange
        val expected = Settings.IconographyType.TWO_TONE

        // Act
        val actual = viewModel.stateFlow.value.iconographyType

        // Assert
        Assert.assertEquals(
            /* message = */ "IconographyType not loaded",
            /* expected = */ expected,
            /* actual = */ actual
        )
    }

    @Test
    fun `Loads the ShapeType upon initialization`() {
        // Arrange
        val expected = Settings.ShapeType.CUT

        // Act
        val actual = viewModel.stateFlow.value.shapeType

        // Assert
        Assert.assertEquals(
            /* message = */ "ShapeType not loaded",
            /* expected = */ expected,
            /* actual = */ actual
        )
    }

    @Test
    fun `Loads the NavigationLabelVisibility upon initialization`() {
        // Arrange
        val expected = Settings.NavigationLabelVisibility.NEVER

        // Act
        val actual = viewModel.stateFlow.value.navigationLabelVisibility

        // Assert
        Assert.assertEquals(
            /* message = */ "NavigationLabelVisibility not loaded",
            /* expected = */ expected,
            /* actual = */ actual
        )
    }

    @Test
    fun `Reflects updates to the ThemeType`() = runTest(dispatcher) {
        // Arrange
        val expected = Settings.ThemeType.DARK

        // Act

        cache.setThemeType(expected)

        val actual = viewModel.stateFlow.value.themeType

        // Assert
        Assert.assertEquals(
            /* message = */ "ThemeType not synchronized",
            /* expected = */ expected,
            /* actual = */ actual
        )
    }

    @Test
    fun `Reflects updates to the IconographyType`() = runTest(dispatcher) {
        // Arrange
        val expected = Settings.IconographyType.ROUNDED

        // Act
        cache.setIconographyType(expected)

        val actual = viewModel.stateFlow.value.iconographyType

        // Assert
        Assert.assertEquals(
            /* message = */ "IconographyType not synchronized",
            /* expected = */ expected,
            /* actual = */ actual
        )
    }

    @Test
    fun `Reflects updates to the ShapeType`() = runTest(dispatcher) {
        // Arrange
        val expected = Settings.ShapeType.CUT

        // Act
        cache.setShapeType(expected)

        val actual = viewModel.stateFlow.value.shapeType

        // Assert
        Assert.assertEquals(
            /* message = */ "ShapeType not synchronized",
            /* expected = */ expected,
            /* actual = */ actual
        )
    }

    @Test
    fun `Reflects updates to the NavigationLabelVisibility`() = runTest(dispatcher) {
        // Arrange
        val expected = Settings.NavigationLabelVisibility.ALWAYS

        // Act
        cache.setNavigationLabelVisibility(expected)

        val actual = viewModel.stateFlow.value.navigationLabelVisibility

        // Assert
        Assert.assertEquals(
            /* message = */ "NavigationLabelVisibility not synchronized",
            /* expected = */ expected,
            /* actual = */ actual
        )
    }

}