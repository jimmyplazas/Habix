package dev.alejo.habix.habits.domain.usecase

data class HomeUseCases(
    val getAllHabitsForSelectedDate: GetAllHabitsForSelectedDate,
    val completeHabitUseCase: CompleteHabitUseCase
)