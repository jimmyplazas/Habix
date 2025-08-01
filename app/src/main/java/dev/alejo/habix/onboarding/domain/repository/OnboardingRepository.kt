package dev.alejo.habix.onboarding.domain.repository

interface OnboardingRepository {
    fun hasSeenOnboarding() : Boolean
    fun completeOnboarding()
}