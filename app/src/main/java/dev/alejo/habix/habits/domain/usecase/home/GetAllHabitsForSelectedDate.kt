package dev.alejo.habix.habits.domain.usecase.home

import dev.alejo.habix.habits.domain.model.Habit
import dev.alejo.habix.habits.domain.repository.HabitRepository
import kotlinx.coroutines.flow.Flow
import java.time.ZonedDateTime

class GetAllHabitsForSelectedDate(
    private val repository: HabitRepository
) {
    operator fun invoke(date: ZonedDateTime): Flow<List<Habit>> {
        return repository.getAllHabitsForSelectedDate(date)
    }
}