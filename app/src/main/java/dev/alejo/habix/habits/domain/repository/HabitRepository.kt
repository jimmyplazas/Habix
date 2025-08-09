package dev.alejo.habix.habits.domain.repository

import dev.alejo.habix.habits.domain.model.Habit
import kotlinx.coroutines.flow.Flow
import java.time.ZonedDateTime

interface HabitRepository {
    fun getAllHabitsForSelectedDate(date: ZonedDateTime): Flow<List<Habit>>
    suspend fun fetchHabitsFromApi()
    suspend fun insertHabit(habit: Habit)
    suspend fun getHabitById(habitId: String): Habit
}