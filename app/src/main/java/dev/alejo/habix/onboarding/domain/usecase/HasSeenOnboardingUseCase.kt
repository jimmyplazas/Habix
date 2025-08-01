package dev.alejo.habix.onboarding.domain.usecase

import dev.alejo.habix.onboarding.domain.repository.OnboardingRepository

class HasSeenOnboardingUseCase(
    private val repository: OnboardingRepository
) {
    operator fun invoke(): Boolean = repository.hasSeenOnboarding()
}