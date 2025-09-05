package dev.alejo.authentication_presentation.signup

sealed class SignUpEvent {
    data class EmailChanged(val email: String) : SignUpEvent()
    data class PasswordChanged(val password: String) : SignUpEvent()
    object SignUp : SignUpEvent()
    object Login : SignUpEvent()
}