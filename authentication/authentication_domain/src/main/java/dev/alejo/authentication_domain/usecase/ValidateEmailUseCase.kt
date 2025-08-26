package dev.alejo.authentication_domain.usecase

import dev.alejo.authentication_domain.matcher.EmailMatcher

class ValidateEmailUseCase(
    private val emailMatcher: EmailMatcher
) {
    operator fun invoke(email: String): Boolean = emailMatcher.isValid(email)
}