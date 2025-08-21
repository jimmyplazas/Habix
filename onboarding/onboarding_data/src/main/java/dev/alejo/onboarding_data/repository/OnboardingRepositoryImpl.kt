package dev.alejo.onboarding_data.repository

import android.content.SharedPreferences
import androidx.core.content.edit
import dev.alejo.onboarding_domain.repository.OnboardingRepository

class OnboardingRepositoryImpl(
    private val prefs: SharedPreferences
) : OnboardingRepository {

    companion object {
        private const val HAS_SEEN_ONBOARDING = "has_seen_onboarding"
    }

    override fun hasSeenOnboarding(): Boolean = prefs
        .getBoolean(HAS_SEEN_ONBOARDING, false)

    override fun completeOnboarding() {
        prefs.edit {
            putBoolean(HAS_SEEN_ONBOARDING, true)
        }
    }

}