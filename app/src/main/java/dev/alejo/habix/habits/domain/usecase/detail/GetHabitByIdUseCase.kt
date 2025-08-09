package dev.alejo.habix.habits.domain.usecase.detail

import dev.alejo.habix.habits.domain.model.Habit
import dev.alejo.habix.habits.domain.repository.HabitRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetHabitByIdUseCase(
    private val repository: HabitRepository
) {
    suspend operator fun invoke(habitId: String): Habit = withContext(Dispatchers.IO) {
        repository.getHabitById(habitId)
    }
}