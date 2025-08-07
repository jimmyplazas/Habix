package dev.alejo.habix.habits.presentation.home

sealed class HomeEffect {
    data class NavigateToDetail(val habitId: String? = null) : HomeEffect()
}