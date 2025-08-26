package dev.alejo.authentication_presentation.signup

sealed class SignUpEffect {
    object NavigateToHome : SignUpEffect()
    object NavigateToLogin : SignUpEffect()
}