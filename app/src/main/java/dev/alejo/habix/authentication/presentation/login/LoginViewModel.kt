package dev.alejo.habix.authentication.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.alejo.habix.authentication.domain.usecase.LoginUseCases
import dev.alejo.habix.authentication.domain.usecase.PasswordResult
import dev.alejo.habix.authentication.presentation.util.PasswordParser
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCases: LoginUseCases
) : ViewModel() {

    private val _state = MutableStateFlow(LoginState())
    val state = _state.asStateFlow()
    private val _effect = Channel<LoginEffect>(Channel.BUFFERED)
    val effect = _effect.receiveAsFlow()

    fun onEvent(event: LoginEvent) {
        when (event) {
            is LoginEvent.EmailChange -> _state.update { _state.value.copy(email = event.email) }
            is LoginEvent.PasswordChange -> _state.update { _state.value.copy(password = event.password) }
            LoginEvent.Login -> login()
            LoginEvent.SignUp -> {
                viewModelScope.launch {
                    _effect.send(LoginEffect.NavigateToSignUp)
                }
            }
        }
    }

    private fun login() {
        val currentState = _state.value
        var hasError = false

        if (!loginUseCases.validateEmail(currentState.email)) {
            _state.update { it.copy(emailError = "Invalid email") }
            hasError = true
        }

        val passwordResult = loginUseCases.validatePassword(currentState.password)
        if (passwordResult != PasswordResult.VALID) {
            _state.update { it.copy(passwordError = PasswordParser.fromValidationResult(passwordResult)) }
            hasError = true
        }

        if(hasError) return

        _state.update { it.copy(isLoading = true) }

        viewModelScope.launch {
            loginUseCases.loginWithEmail(currentState.email, currentState.password)
                .onSuccess {
                    _effect.send(LoginEffect.NavigateToHome)
                }
                .onFailure { error ->
                    _state.update { it.copy(emailError = error.message) }
                }
            _state.update { it.copy(isLoading = false) }
        }
    }

}