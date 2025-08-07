package dev.alejo.habix.habits.domain.usecase.detail

import dev.alejo.habix.habits.domain.model.Habit
import dev.alejo.habix.habits.domain.repository.HabitRepository

class GetHabitByIdUseCase(
    private val repository: HabitRepository
) {
    operator fun invoke(habitId: String): Habit = repository.getHabitById(habitId)
}