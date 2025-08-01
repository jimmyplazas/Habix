package dev.alejo.habix.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.entry
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import dev.alejo.habix.onboarding.presentation.OnboardingScreen

@Composable
fun NavigationHost(modifier: Modifier = Modifier) {
    val backStack = rememberNavBackStack(NavigationScreens.Onboarding)
    NavDisplay(
        modifier = modifier,
        backStack = backStack,
        entryProvider = entryProvider {
            entry<NavigationScreens.Onboarding> {
                OnboardingScreen {
                    println("Onboarding finished")
                }
            }
        }
    )
}