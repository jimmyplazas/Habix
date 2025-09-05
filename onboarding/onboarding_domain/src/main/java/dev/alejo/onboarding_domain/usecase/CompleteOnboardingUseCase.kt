package dev.alejo.onboarding_domain.usecase

import dev.alejo.onboarding_domain.repository.OnboardingRepository

class CompleteOnboardingUseCase(
    private val repository: OnboardingRepository
) {
    operator fun invoke() = repository.completeOnboarding()
}