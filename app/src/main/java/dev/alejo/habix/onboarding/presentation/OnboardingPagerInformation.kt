package dev.alejo.habix.onboarding.presentation

import androidx.annotation.DrawableRes

data class OnboardingPagerInformation(
    val title: String,
    val subtitle: String,
    @param:DrawableRes val image: Int
)
