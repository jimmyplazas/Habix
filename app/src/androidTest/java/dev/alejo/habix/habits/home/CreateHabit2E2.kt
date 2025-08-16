package dev.alejo.habix.habits.home

import android.util.Log
import androidx.activity.compose.setContent
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.entry
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSavedStateNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import androidx.navigation3.ui.rememberSceneSetupNavEntryDecorator
import androidx.test.platform.app.InstrumentationRegistry
import androidx.work.Configuration
import androidx.work.testing.SynchronousExecutor
import androidx.work.testing.WorkManagerTestInitHelper
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dev.alejo.habix.MainActivity
import dev.alejo.habix.habits.domain.usecase.detail.DetailUseCases
import dev.alejo.habix.habits.domain.usecase.detail.GetHabitByIdUseCase
import dev.alejo.habix.habits.domain.usecase.detail.InsertHabitUseCase
import dev.alejo.habix.habits.domain.usecase.home.CompleteHabitUseCase
import dev.alejo.habix.habits.domain.usecase.home.GetAllHabitsForSelectedDate
import dev.alejo.habix.habits.domain.usecase.home.HabitUseCases
import dev.alejo.habix.habits.domain.usecase.home.HomeHabitSyncUseCase
import dev.alejo.habix.habits.home.data.repository.FakeHomeRepository
import dev.alejo.habix.habits.presentation.detail.DetailScreen
import dev.alejo.habix.habits.presentation.detail.DetailViewModel
import dev.alejo.habix.habits.presentation.home.HomeScreen
import dev.alejo.habix.habits.presentation.home.HomeViewModel
import dev.alejo.habix.navigation.NavigationScreens
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class CreateHabit2E2 {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @get:Rule
    val composeRule = createAndroidComposeRule<MainActivity>()

    private lateinit var homeRepository: FakeHomeRepository
    private lateinit var homeViewModel: HomeViewModel
    private lateinit var detailViewModel: DetailViewModel

    @Before
    fun setUp() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        val config = Configuration.Builder()
            .setMinimumLoggingLevel(Log.DEBUG)
            .setExecutor(SynchronousExecutor())
            .build()

        WorkManagerTestInitHelper.initializeTestWorkManager(context, config)
        homeRepository = FakeHomeRepository()
        val homeUseCases = HabitUseCases(
            getAllHabitsForSelectedDate = GetAllHabitsForSelectedDate(homeRepository),
            completeHabitUseCase = CompleteHabitUseCase(homeRepository),
            syncHabitsUseCase = HomeHabitSyncUseCase(homeRepository)
        )
        homeViewModel = HomeViewModel(homeUseCases)

        val detailUseCases = DetailUseCases(
            getHabitByIdUseCase = GetHabitByIdUseCase(homeRepository),
            insertHabitUseCase = InsertHabitUseCase(homeRepository)
        )
        detailViewModel = DetailViewModel(null, detailUseCases)

        composeRule.activity.setContent {
            val backStack = rememberNavBackStack(NavigationScreens.Home)
            NavDisplay(
                backStack = backStack,
                entryDecorators = listOf(
                    rememberSceneSetupNavEntryDecorator(),
                    rememberSavedStateNavEntryDecorator(),
                    rememberViewModelStoreNavEntryDecorator()
                ),
                entryProvider = entryProvider {

                    entry<NavigationScreens.Home> {
                        HomeScreen(
                            navigateToDetail = { habitId ->
                                backStack.add(NavigationScreens.Detail(habitId))
                            },
                            navigateToSettings = {
                                backStack.add(NavigationScreens.Settings)
                            },
                            navigateBack = {
                                backStack.removeLastOrNull()
                            },
                            viewModel = homeViewModel
                        )
                    }
                    entry<NavigationScreens.Detail> {
                        DetailScreen(habitId = it.habitId) {
                            backStack.removeLastOrNull()
                        }
                    }
                }
            )
        }
    }

    @Test
    fun createHabit() {
        composeRule.onNodeWithText("Home").assertIsDisplayed()
    }

}