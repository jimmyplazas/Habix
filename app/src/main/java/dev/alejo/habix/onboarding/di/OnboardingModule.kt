package dev.alejo.habix.onboarding.di

import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dev.alejo.habix.onboarding.data.repository.OnboardingRepositoryImpl
import dev.alejo.habix.onboarding.domain.repository.OnboardingRepository
import dev.alejo.habix.onboarding.domain.usecase.CompleteOnboardingUseCase
import dev.alejo.habix.onboarding.domain.usecase.HasSeenOnboardingUseCase
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object OnboardingModule {

    @Provides
    @Singleton
    fun provideSharedPreferences(
        @ApplicationContext context: Context
    ): SharedPreferences = context
        .getSharedPreferences("habix_onboarding_prefs", Context.MODE_PRIVATE)

    @Provides
    @Singleton
    fun provideOnboardingRepository(
        prefs: SharedPreferences
    ): OnboardingRepository = OnboardingRepositoryImpl(prefs)

    @Provides
    @Singleton
    fun provideHasSeenOnboardingUseCase(
        repository: OnboardingRepository
    ): HasSeenOnboardingUseCase = HasSeenOnboardingUseCase(repository)

    @Provides
    @Singleton
    fun provideCompleteOnboardingUseCase(
        repository: OnboardingRepository
    ): CompleteOnboardingUseCase = CompleteOnboardingUseCase(repository)

}