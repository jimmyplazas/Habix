package dev.alejo.habits_presentation.home

import dev.alejo.habits_domain.model.Habit
import java.time.ZonedDateTime

sealed class HomeEvent {
    data class ChangeDate(val date: ZonedDateTime) : HomeEvent()
    data class CompleteHabit(val habit: Habit) : HomeEvent()
    data class EditHabit(val habitId: String) : HomeEvent()
    data object AddHabit : HomeEvent()
    data object GoBack : HomeEvent()
    data object GoSettings : HomeEvent()
}