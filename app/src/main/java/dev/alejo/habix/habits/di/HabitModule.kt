package dev.alejo.habix.habits.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.alejo.habix.habits.data.repository.HabitRepositoryImpl
import dev.alejo.habix.habits.domain.repository.HabitRepository
import dev.alejo.habix.habits.domain.usecase.detail.DetailUseCases
import dev.alejo.habix.habits.domain.usecase.detail.GetHabitByIdUseCase
import dev.alejo.habix.habits.domain.usecase.detail.InsertHabitUseCase
import dev.alejo.habix.habits.domain.usecase.home.CompleteHabitUseCase
import dev.alejo.habix.habits.domain.usecase.home.GetAllHabitsForSelectedDate
import dev.alejo.habix.habits.domain.usecase.home.HabitUseCases
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HabitModule {

    @Provides
    @Singleton
    fun provideHomeRepository(): HabitRepository = HabitRepositoryImpl()

    @Provides
    @Singleton
    fun provideHabitUseCases(repository: HabitRepository): HabitUseCases {
        return HabitUseCases(
            getAllHabitsForSelectedDate = GetAllHabitsForSelectedDate(repository),
            completeHabitUseCase = CompleteHabitUseCase(repository)
        )
    }

    @Provides
    @Singleton
    fun provideDetailUseCases(repository: HabitRepository): DetailUseCases {
        return DetailUseCases(
            getHabitByIdUseCase = GetHabitByIdUseCase(repository),
            insertHabitUseCase = InsertHabitUseCase(repository)
        )
    }


}