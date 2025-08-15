package dev.alejo.habix.habits.domain.usecase.detail

import dev.alejo.habix.habits.domain.model.Habit
import dev.alejo.habix.habits.domain.repository.HabitRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class InsertHabitUseCase (
    private val repository: HabitRepository
) {
    suspend operator fun invoke(habit: Habit) = withContext(Dispatchers.IO) {
        repository.insertHabit(habit)
    }
}