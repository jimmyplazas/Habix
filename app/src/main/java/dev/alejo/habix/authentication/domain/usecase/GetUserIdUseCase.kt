package dev.alejo.habix.authentication.domain.usecase

import dev.alejo.habix.core.session.SessionManager

class GetUserIdUseCase(
    private val sessionManager: SessionManager
) {
    operator fun invoke(): String? = sessionManager.getUserId()
}