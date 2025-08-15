package dev.alejo.habix.authentication.presentation.signup

sealed class SignUpEffect {
    object NavigateToHome : SignUpEffect()
    object NavigateToLogin : SignUpEffect()
}