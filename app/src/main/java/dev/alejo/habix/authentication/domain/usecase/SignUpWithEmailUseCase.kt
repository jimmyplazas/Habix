package dev.alejo.habix.authentication.domain.usecase

import dev.alejo.habix.authentication.domain.repository.AuthRepository

class SignUpWithEmailUseCase(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(
        email: String,
        password: String
    ): Result<Unit> = repository.signUp(email, password)
}