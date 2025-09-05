package dev.alejo.habits_domain.repository

import dev.alejo.habits_domain.model.Habit
import kotlinx.coroutines.flow.Flow
import java.time.ZonedDateTime

interface HabitRepository {
    fun getAllHabitsForSelectedDate(date: ZonedDateTime): Flow<List<Habit>>
    suspend fun fetchHabitsFromApi()
    suspend fun insertHabit(habit: Habit)
    suspend fun getHabitById(habitId: String): Habit
    suspend fun syncHabits()
}