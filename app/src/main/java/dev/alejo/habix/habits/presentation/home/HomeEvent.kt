package dev.alejo.habix.habits.presentation.home

import dev.alejo.habix.habits.domain.model.Habit
import java.time.ZonedDateTime

sealed class HomeEvent {
    data class GangeDate(val date: ZonedDateTime) : HomeEvent()
    data class CompleteHabit(val habit: Habit) : HomeEvent()
}