package dev.alejo.habix.habits.data.repository

import dev.alejo.habix.habits.domain.model.Habit
import dev.alejo.habix.habits.domain.repository.HabitRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import java.time.LocalDate
import java.time.ZonedDateTime

class HabitRepositoryImpl : HabitRepository {

    private val mockHabits = (1..3).map {
        val dates = if (it % 2 == 0) {
            listOf(LocalDate.now())
        } else emptyList()
        Habit(
            id = it.toString(),
            name = "Habit $it",
            frequency = emptyList(),
            completedDates = dates,
            reminder = ZonedDateTime.now().toLocalTime(),
            startDate = ZonedDateTime.now()
        )
    }.toMutableList()

    override fun getAllHabitsForSelectedDate(date: ZonedDateTime): Flow<List<Habit>> {
        return flowOf(mockHabits)
    }

    override suspend fun insertHabit(habit: Habit) {
        val index = mockHabits.indexOfFirst { habit.id == it.id }
        if (index == -1) {
            mockHabits.add(habit)
        } else {
            mockHabits.removeAt(index)
            mockHabits.add(index, habit)
        }
    }

    override fun getHabitById(habitId: String): Habit = mockHabits.first { it.id == habitId }
}