package dev.alejo.authentication_domain.usecase

import dev.alejo.core_domain.session.SessionManager

class GetUserIdUseCase(
    private val sessionManager: SessionManager
) {
    operator fun invoke(): String? = sessionManager.getUserId()
}