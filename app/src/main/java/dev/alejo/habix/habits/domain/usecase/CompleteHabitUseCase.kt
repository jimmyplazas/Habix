package dev.alejo.habix.habits.domain.usecase

import dev.alejo.habix.habits.domain.model.Habit
import dev.alejo.habix.habits.domain.repository.HomeRepository
import java.time.ZonedDateTime

class CompleteHabitUseCase (
    private val repository: HomeRepository
) {
    suspend operator fun invoke(habit: Habit, date: ZonedDateTime) {
        val newHabit = if (habit.completedDates.contains(date.toLocalDate())) {
            habit.copy(completedDates = habit.completedDates - date.toLocalDate())
        } else {
            habit.copy(completedDates = habit.completedDates + date.toLocalDate())
        }
        repository.insertHabit(newHabit)
    }
}