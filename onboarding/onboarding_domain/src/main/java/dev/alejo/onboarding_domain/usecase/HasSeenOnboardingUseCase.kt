package dev.alejo.onboarding_domain.usecase

import dev.alejo.onboarding_domain.repository.OnboardingRepository

class HasSeenOnboardingUseCase(
    private val repository: OnboardingRepository
) {
    operator fun invoke(): Boolean = repository.hasSeenOnboarding()
}