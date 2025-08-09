package dev.alejo.habix.habits.data.mapper

import dev.alejo.habix.habits.data.extension.toStartOfDayTimeStamp
import dev.alejo.habix.habits.data.extension.toTimeStamp
import dev.alejo.habix.habits.data.extension.toZonedDateTime
import dev.alejo.habix.habits.data.local.entity.HabitEntity
import dev.alejo.habix.habits.data.remote.dto.HabitDto
import dev.alejo.habix.habits.data.remote.dto.HabitResponse
import dev.alejo.habix.habits.domain.model.Habit
import java.time.DayOfWeek

fun HabitEntity.toDomain(): Habit = Habit(
    id = id,
    name = name,
    frequency = frequency.map { DayOfWeek.of(it) },
    completedDates = completedDates.map { it.toZonedDateTime().toLocalDate() },
    reminder = reminder.toZonedDateTime().toLocalTime(),
    startDate = startDate.toZonedDateTime()
)

fun Habit.toEntity() = HabitEntity(
    id = id,
    name = name,
    frequency = frequency.map { it.value },
    completedDates = completedDates.map { it.toZonedDateTime().toTimeStamp() },
    reminder = reminder.toZonedDateTime().toTimeStamp(),
    startDate = startDate.toStartOfDayTimeStamp()
)

fun HabitResponse.toDomain(): List<Habit> {
    return this.entries.map { habitResponse ->
        val (habitId, habit) = habitResponse
        Habit(
            id = habitId,
            name = habit.name,
            frequency = habit.frequency.map { DayOfWeek.of(it) },
            completedDates = habit.completedDates?.map {
                it.toZonedDateTime().toLocalDate()
            }?: emptyList(),
            reminder = habit.reminder.toZonedDateTime().toLocalTime(),
            startDate = habit.startDate.toZonedDateTime()
        )
    }
}

fun Habit.toDto() : HabitResponse {
    val habit = HabitDto(
        name = name,
        frequency = frequency.map { it.value },
        completedDates = completedDates.map { it.toZonedDateTime().toTimeStamp() },
        reminder = reminder.toZonedDateTime().toTimeStamp(),
        startDate = startDate.toStartOfDayTimeStamp()
    )
    return mapOf(id to habit)
}