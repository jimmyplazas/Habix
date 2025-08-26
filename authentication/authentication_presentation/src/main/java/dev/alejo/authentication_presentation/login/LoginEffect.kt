package dev.alejo.authentication_presentation.login

sealed class LoginEffect {
    object NavigateToHome : LoginEffect()
    object NavigateToSignUp : LoginEffect()
}