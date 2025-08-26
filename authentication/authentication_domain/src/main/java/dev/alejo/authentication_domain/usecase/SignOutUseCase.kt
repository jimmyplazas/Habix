package dev.alejo.authentication_domain.usecase

import dev.alejo.authentication_domain.repository.AuthRepository

class SignOutUseCase(
    private val repository: AuthRepository
) {
    operator fun invoke() = repository.logout()
}