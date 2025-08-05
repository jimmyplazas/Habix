package dev.alejo.habix.authentication.domain.usecase

data class LoginUseCases(
    val loginWithEmail: LoginWithEmailUseCase,
    val validateEmail: ValidateEmailUseCase,
    val validatePassword: ValidatePasswordUseCase
)