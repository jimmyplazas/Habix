package dev.alejo.habix.navigation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.entry
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import dev.alejo.habix.onboarding.presentation.OnboardingScreen

@Composable
fun NavigationHost(
    modifier: Modifier = Modifier,
    startDestination: NavigationScreens = NavigationScreens.Onboarding
) {
    val backStack = rememberNavBackStack(startDestination)
    NavDisplay(
        modifier = modifier,
        backStack = backStack,
        entryProvider = entryProvider {
            entry<NavigationScreens.Onboarding> {
                OnboardingScreen {
                    backStack.remove(NavigationScreens.Onboarding)
                    backStack.add(NavigationScreens.Login)
                }
            }
            entry<NavigationScreens.Login> {
                Text("Login")
            }
        }
    )
}