package dev.alejo.habix.habits.domain.usecase.home

import dev.alejo.habix.habits.domain.repository.HabitRepository

class HomeHabitSyncUseCase(
    private val repository: HabitRepository
) {
    suspend operator fun invoke() = repository.syncHabits()
}