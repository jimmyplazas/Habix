package dev.alejo.habix

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.alejo.authentication_domain.usecase.GetUserIdUseCase
import dev.alejo.authentication_domain.usecase.SignOutUseCase
import dev.alejo.onboarding_domain.usecase.HasSeenOnboardingUseCase
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val hasSeenOnboardingUseCase: HasSeenOnboardingUseCase,
    private val getUSerIdUseCase: GetUserIdUseCase,
    private val signOutUseCase: SignOutUseCase
) : ViewModel() {

    var hasSeenOnboarding by mutableStateOf(hasSeenOnboardingUseCase())
        private set
    var isLoggedIn by mutableStateOf(getUSerIdUseCase() != null)
        private set

    fun signOut() {
        signOutUseCase()
    }

}