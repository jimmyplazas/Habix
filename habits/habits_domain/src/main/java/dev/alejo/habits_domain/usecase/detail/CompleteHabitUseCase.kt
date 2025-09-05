package dev.alejo.habits_domain.usecase.detail

import dev.alejo.habits_domain.model.Habit
import dev.alejo.habits_domain.repository.HabitRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class InsertHabitUseCase (
    private val repository: HabitRepository
) {
    suspend operator fun invoke(habit: Habit) = withContext(Dispatchers.IO) {
        repository.insertHabit(habit)
    }
}