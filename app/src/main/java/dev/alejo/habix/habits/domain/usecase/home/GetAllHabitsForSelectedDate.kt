package dev.alejo.habix.habits.domain.usecase.home

import dev.alejo.habix.habits.domain.model.Habit
import dev.alejo.habix.habits.domain.repository.HabitRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import java.time.ZonedDateTime

class GetAllHabitsForSelectedDate(
    private val repository: HabitRepository
) {
    operator fun invoke(date: ZonedDateTime): Flow<List<Habit>> {
        return repository.getAllHabitsForSelectedDate(date).distinctUntilChanged()
            .map { habits ->
                val habitsFiltered = habits.filter { habit ->
                    habit.frequency.contains(date.dayOfWeek)
                }
                habitsFiltered.sortedBy { it.name }
            }
            .onStart { repository.fetchHabitsFromApi() }
    }
}