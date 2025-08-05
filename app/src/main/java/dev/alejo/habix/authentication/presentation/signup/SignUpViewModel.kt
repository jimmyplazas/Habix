package dev.alejo.habix.authentication.presentation.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.alejo.habix.authentication.domain.usecase.PasswordResult
import dev.alejo.habix.authentication.domain.usecase.SignUpUseCases
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val signUpUseCases: SignUpUseCases
) : ViewModel() {

    private val _state = MutableStateFlow(SignUpState())
    val state: StateFlow<SignUpState> = _state.asStateFlow()

    private val _effect = Channel<SignUpEffect>(Channel.BUFFERED)
    val effect = _effect.receiveAsFlow()

    fun onEvent(event: SignUpEvent) {
        when (event) {
            is SignUpEvent.EmailChanged -> {
                _state.update { it.copy(email = event.email) }
            }
            is SignUpEvent.PasswordChanged -> {
                _state.update { it.copy(password = event.password) }
            }
            is SignUpEvent.SignUp -> signUp()
            SignUpEvent.Login -> {
                viewModelScope.launch {
                    _effect.send(SignUpEffect.NavigateToLogin)
                }
            }
        }
    }

    private fun signUp() {
        val currentState = _state.value
        var hasError = false

        if (!signUpUseCases.validateEmail(currentState.email)) {
            _state.update { it.copy(emailError = "Invalid email") }
            hasError = true
        }

        val passwordResult = signUpUseCases.validatePassword(currentState.password)
        if (passwordResult is PasswordResult.Invalid) {
            _state.update { it.copy(passwordError = passwordResult.message) }
            hasError = true
        }

        if (hasError) return

        _state.update { it.copy(isLoading = true) }

        viewModelScope.launch {
            signUpUseCases.signUpWithEmail(currentState.email, currentState.password)
                .onSuccess {
                    _effect.send(SignUpEffect.NavigateToHome)
                }
                .onFailure { error ->
                    _state.update { it.copy(emailError = error.message) }
                }

            _state.update { it.copy(isLoading = false) }
        }
    }

}