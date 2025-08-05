package dev.alejo.habix.authentication.domain.usecase

data class SignUpUseCases(
    val signUpWithEmail: SignUpWithEmailUseCase,
    val validateEmail: ValidateEmailUseCase,
    val validatePassword: ValidatePasswordUseCase
)