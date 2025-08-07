package dev.alejo.habix.habits.domain.usecase.detail

import dev.alejo.habix.habits.domain.model.Habit
import dev.alejo.habix.habits.domain.repository.HabitRepository

class InsertHabitUseCase (
    private val repository: HabitRepository
) {
    suspend operator fun invoke(habit: Habit) = repository.insertHabit(habit)
}