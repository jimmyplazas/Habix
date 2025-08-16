package dev.alejo.habix.habits.home.data.repository

import dev.alejo.habix.habits.domain.model.Habit
import dev.alejo.habix.habits.domain.repository.HabitRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import java.time.ZonedDateTime

class FakeHomeRepository : HabitRepository {

    private var habits = emptyList<Habit>()
    private val habitsFlow = MutableSharedFlow<List<Habit>>()

    override fun getAllHabitsForSelectedDate(date: ZonedDateTime): Flow<List<Habit>> = habitsFlow

    override suspend fun fetchHabitsFromApi() { }

    override suspend fun insertHabit(habit: Habit) {
        habits = habits + habit
        habitsFlow.emit(habits)
    }

    override suspend fun getHabitById(habitId: String): Habit = habits.first { it.id == habitId }

    override suspend fun syncHabits() { }
}