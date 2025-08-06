package dev.alejo.habix.habits.domain.usecase

import dev.alejo.habix.habits.domain.model.Habit
import dev.alejo.habix.habits.domain.repository.HomeRepository
import kotlinx.coroutines.flow.Flow
import java.time.ZonedDateTime

class GetAllHabitsForSelectedDate(
    private val repository: HomeRepository
) {
    operator fun invoke(date: ZonedDateTime): Flow<List<Habit>> {
        return repository.getAllHabitsForSelectedDate(date)
    }
}