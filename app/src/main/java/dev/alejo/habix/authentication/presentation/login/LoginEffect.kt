package dev.alejo.habix.authentication.presentation.login

sealed class LoginEffect {
    object NavigateToHome : LoginEffect()
    object NavigateToSignUp : LoginEffect()
}