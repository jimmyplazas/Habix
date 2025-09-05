package dev.alejo.habits_data.mapper

import dev.alejo.habits_data.extension.toStartOfDayTimeStamp
import dev.alejo.habits_data.extension.toTimeStamp
import dev.alejo.habits_data.extension.toZonedDateTime
import dev.alejo.habits_data.local.entity.HabitEntity
import dev.alejo.habits_data.local.entity.HabitSyncEntity
import dev.alejo.habits_data.remote.dto.HabitDto
import dev.alejo.habits_data.remote.dto.HabitResponse
import dev.alejo.habits_domain.model.Habit
import java.time.DayOfWeek

fun HabitEntity.toDomain(): Habit = Habit(
    id = id,
    userId = userId,
    name = name,
    frequency = frequency.map { DayOfWeek.of(it) },
    completedDates = completedDates.map { it.toZonedDateTime().toLocalDate() },
    reminder = reminder.toZonedDateTime().toLocalTime(),
    startDate = startDate.toZonedDateTime()
)

fun Habit.toEntity() = HabitEntity(
    id = id,
    userId = userId,
    name = name,
    frequency = frequency.map { it.value },
    completedDates = completedDates.map { it.toZonedDateTime().toTimeStamp() },
    reminder = reminder.toZonedDateTime().toTimeStamp(),
    startDate = startDate.toStartOfDayTimeStamp()
)

fun HabitResponse.toDomain(userId: String): List<Habit> {
    return this.entries.map { habitResponse ->
        val (habitId, habit) = habitResponse
        Habit(
            id = habitId,
            userId = userId,
            name = habit.name,
            frequency = habit.frequency.map { DayOfWeek.of(it) },
            completedDates = habit.completedDates?.map {
                it.toZonedDateTime().toLocalDate()
            } ?: emptyList(),
            reminder = habit.reminder.toZonedDateTime().toLocalTime(),
            startDate = habit.startDate.toZonedDateTime()
        )
    }
}

fun Habit.toDto(): HabitResponse {
    val habit = HabitDto(
        name = name,
        frequency = frequency.map { it.value },
        completedDates = completedDates.map { it.toZonedDateTime().toTimeStamp() },
        reminder = reminder.toZonedDateTime().toTimeStamp(),
        startDate = startDate.toStartOfDayTimeStamp()
    )
    return mapOf(id to habit)
}

fun Habit.toHabitSyncEntity(userId: String): HabitSyncEntity =
    HabitSyncEntity(id = id, userId = userId)