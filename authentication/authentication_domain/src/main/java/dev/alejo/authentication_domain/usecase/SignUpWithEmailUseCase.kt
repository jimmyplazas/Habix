package dev.alejo.authentication_domain.usecase

import dev.alejo.authentication_domain.repository.AuthRepository

class SignUpWithEmailUseCase(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(
        email: String,
        password: String
    ): Result<Unit> = repository.signUp(email, password)
}