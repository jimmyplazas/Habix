package dev.alejo.authentication_domain.usecase

data class LoginUseCases(
    val loginWithEmail: LoginWithEmailUseCase,
    val validateEmail: ValidateEmailUseCase,
    val validatePassword: ValidatePasswordUseCase
)