package dev.alejo.habits_domain.usecase.detail

import dev.alejo.habits_domain.model.Habit
import dev.alejo.habits_domain.repository.HabitRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetHabitByIdUseCase(
    private val repository: HabitRepository
) {
    suspend operator fun invoke(habitId: String): Habit = withContext(Dispatchers.IO) {
        repository.getHabitById(habitId)
    }
}