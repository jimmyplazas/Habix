package dev.alejo.habits_domain.usecase.detail

data class DetailUseCases(
    val getHabitByIdUseCase: GetHabitByIdUseCase,
    val insertHabitUseCase: InsertHabitUseCase
)