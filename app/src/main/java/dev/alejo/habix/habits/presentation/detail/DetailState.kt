package dev.alejo.habix.habits.presentation.detail

import java.time.DayOfWeek
import java.time.LocalDate
import java.time.LocalTime
import java.time.ZonedDateTime

data class DetailState(
    val habitId: String? = null,
    val habitName: String = "",
    val frequency: Set<DayOfWeek> = emptySet(),
    val reminder: LocalTime = LocalTime.now(),
    val completedDates: List<LocalDate> = emptyList(),
    val startDate: ZonedDateTime = ZonedDateTime.now()
)