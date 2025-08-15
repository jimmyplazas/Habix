package dev.alejo.habix.onboarding.domain.usecase

import dev.alejo.habix.onboarding.domain.repository.OnboardingRepository

class CompleteOnboardingUseCase(
    private val repository: OnboardingRepository
) {
    operator fun invoke() = repository.completeOnboarding()
}