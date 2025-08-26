package dev.alejo.authentication_domain.usecase

data class SignUpUseCases(
    val signUpWithEmail: SignUpWithEmailUseCase,
    val validateEmail: ValidateEmailUseCase,
    val validatePassword: ValidatePasswordUseCase
)