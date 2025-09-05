package dev.alejo.habits_domain.usecase.home

import dev.alejo.habits_domain.repository.HabitRepository

class HomeHabitSyncUseCase(
    private val repository: HabitRepository
) {
    suspend operator fun invoke() = repository.syncHabits()
}