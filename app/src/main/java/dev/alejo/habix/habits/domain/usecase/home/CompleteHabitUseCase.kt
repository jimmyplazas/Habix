package dev.alejo.habix.habits.domain.usecase.home

import dev.alejo.habix.habits.domain.model.Habit
import dev.alejo.habix.habits.domain.repository.HabitRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.time.ZonedDateTime

class CompleteHabitUseCase (
    private val repository: HabitRepository
) {
    suspend operator fun invoke(habit: Habit, date: ZonedDateTime) = withContext(Dispatchers.IO) {
        val newHabit = if (habit.completedDates.contains(date.toLocalDate())) {
            habit.copy(completedDates = habit.completedDates - date.toLocalDate())
        } else {
            habit.copy(completedDates = habit.completedDates + date.toLocalDate())
        }
        repository.insertHabit(newHabit)
    }
}