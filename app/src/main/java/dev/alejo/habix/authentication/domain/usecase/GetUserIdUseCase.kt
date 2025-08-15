package dev.alejo.habix.authentication.domain.usecase

import dev.alejo.habix.authentication.domain.repository.AuthRepository

class GetUserIdUseCase(
    private val repository: AuthRepository
) {
    operator fun invoke(): String? = repository.getUserId()
}