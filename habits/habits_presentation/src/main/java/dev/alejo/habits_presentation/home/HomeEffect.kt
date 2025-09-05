package dev.alejo.habits_presentation.home

sealed class HomeEffect {
    data class NavigateToDetail(val habitId: String? = null) : HomeEffect()
    data object GoBack : HomeEffect()
    data object GoSettings : HomeEffect()
}