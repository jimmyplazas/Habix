package dev.alejo.habix.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
sealed interface NavigationScreens: NavKey {
    @Serializable
    data object Onboarding : NavigationScreens
}