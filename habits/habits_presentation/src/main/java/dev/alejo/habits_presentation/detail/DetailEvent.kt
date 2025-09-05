package dev.alejo.habits_presentation.detail

import java.time.DayOfWeek
import java.time.LocalTime

sealed class DetailEvent {
    data class HabitNameChange(val habitName: String) : DetailEvent()
    data class FrequencyChange(val dayOfWeek: DayOfWeek) : DetailEvent()
    data class ReminderChange(val time: LocalTime) : DetailEvent()
    data object Save : DetailEvent()
    data object Back : DetailEvent()
}