package dev.alejo.habix.habits.domain.usecase.home

data class HabitUseCases(
    val getAllHabitsForSelectedDate: GetAllHabitsForSelectedDate,
    val completeHabitUseCase: CompleteHabitUseCase
)