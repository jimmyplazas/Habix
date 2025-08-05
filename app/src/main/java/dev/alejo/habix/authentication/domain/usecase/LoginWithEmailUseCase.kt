package dev.alejo.habix.authentication.domain.usecase

import dev.alejo.habix.authentication.domain.repository.AuthRepository

class LoginWithEmailUseCase(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(
        email: String,
        password: String
    ): Result<Unit> = repository.login(email, password)
}