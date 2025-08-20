package dev.alejo.habix.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.entry
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSavedStateNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import androidx.navigation3.ui.rememberSceneSetupNavEntryDecorator
import dev.alejo.habix.authentication.presentation.login.LoginScreen
import dev.alejo.habix.authentication.presentation.signup.SignUpScreen
import dev.alejo.habix.habits.presentation.detail.DetailScreen
import dev.alejo.habix.habits.presentation.home.HomeScreen
import dev.alejo.habix.onboarding.presentation.OnboardingScreen
import dev.alejo.settings_presentation.SettingsScreen

@Composable
fun NavigationHost(
    modifier: Modifier = Modifier,
    startDestination: NavigationScreens = NavigationScreens.Onboarding,
    onSignOut: () -> Unit
) {
    val backStack = rememberNavBackStack(startDestination)
    NavDisplay(
        modifier = modifier,
        backStack = backStack,
        entryDecorators = listOf(
            rememberSceneSetupNavEntryDecorator(),
            rememberSavedStateNavEntryDecorator(),
            rememberViewModelStoreNavEntryDecorator()
        ),
        entryProvider = entryProvider {
            entry<NavigationScreens.Onboarding> {
                OnboardingScreen {
                    backStack.remove(NavigationScreens.Onboarding)
                    backStack.add(NavigationScreens.Login)
                }
            }
            entry<NavigationScreens.Login> {
                LoginScreen(
                    navigateToHome = {
                        backStack.remove(NavigationScreens.Login)
                        backStack.add(NavigationScreens.Home)
                    },
                    navigateToSignUp = {
                        backStack.add(NavigationScreens.SignUp)
                    }

                )
            }
            entry<NavigationScreens.SignUp> {
                SignUpScreen(
                    navigateToHome = {
                        backStack.clear()
                        backStack.add(NavigationScreens.Home)
                    },
                    navigateToLogin = {
                        backStack.removeLastOrNull()
                    }
                )
            }
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
                    }
                )
            }
            entry<NavigationScreens.Detail> {
                DetailScreen(habitId = it.habitId) {
                    backStack.removeLastOrNull()
                }
            }
            entry<NavigationScreens.Settings> {
                SettingsScreen(
                    onBack = {
                        backStack.removeLastOrNull()
                    },
                    onSignOut = {
                        backStack.clear()
                        backStack.add(NavigationScreens.Login)
                        onSignOut()
                    }
                )
            }
        }
    )
}