package dev.alejo.habix.authentication.domain.usecase

import dev.alejo.core_data.session.SessionManager

class GetUserIdUseCase(
    private val sessionManager: SessionManager
) {
    operator fun invoke(): String? = sessionManager.getUserId()
}