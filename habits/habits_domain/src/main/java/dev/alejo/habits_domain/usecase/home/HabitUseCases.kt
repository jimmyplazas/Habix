package dev.alejo.habits_domain.usecase.home

data class HabitUseCases(
    val getAllHabitsForSelectedDate: GetAllHabitsForSelectedDate,
    val completeHabitUseCase: CompleteHabitUseCase,
    val syncHabitsUseCase: HomeHabitSyncUseCase
)