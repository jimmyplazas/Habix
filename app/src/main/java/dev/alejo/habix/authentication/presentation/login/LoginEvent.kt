package dev.alejo.habix.authentication.presentation.login

sealed class LoginEvent {
    data class EmailChange(val email: String) : LoginEvent()
    data class PasswordChange(val password: String) : LoginEvent()
    data object Login : LoginEvent()
    data object SignUp : LoginEvent()
}