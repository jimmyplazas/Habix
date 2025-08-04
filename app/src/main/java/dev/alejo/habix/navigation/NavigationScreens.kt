package dev.alejo.habix.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
sealed class NavigationScreens: NavKey {
    @Serializable
    data object Onboarding : NavigationScreens()
    @Serializable
    data object Login : NavigationScreens()
    @Serializable
    data object SignUp : NavigationScreens()
}