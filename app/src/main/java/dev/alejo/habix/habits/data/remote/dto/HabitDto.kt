package dev.alejo.habix.habits.data.remote.dto

data class HabitDto(
    val name: String,
    val frequency: List<Int>,
    val completedDates: List<Long>?,
    val reminder: Long,
    val startDate: Long
)