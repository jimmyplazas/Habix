package dev.alejo.habix.habits.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.alejo.habix.habits.data.repository.HomeRepositoryImpl
import dev.alejo.habix.habits.domain.repository.HomeRepository
import dev.alejo.habix.habits.domain.usecase.CompleteHabitUseCase
import dev.alejo.habix.habits.domain.usecase.GetAllHabitsForSelectedDate
import dev.alejo.habix.habits.domain.usecase.HomeUseCases
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HomeModule {

    @Provides
    @Singleton
    fun provideHomeRepository(): HomeRepository = HomeRepositoryImpl()

    @Provides
    @Singleton
    fun provideHomeUseCases(repository: HomeRepository): HomeUseCases {
        return HomeUseCases(
            getAllHabitsForSelectedDate = GetAllHabitsForSelectedDate(repository),
            completeHabitUseCase = CompleteHabitUseCase(repository)
        )
    }


}