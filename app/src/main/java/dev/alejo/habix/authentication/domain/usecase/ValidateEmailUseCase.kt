package dev.alejo.habix.authentication.domain.usecase

import dev.alejo.habix.authentication.domain.matcher.EmailMatcher

class ValidateEmailUseCase(
    private val emailMatcher: EmailMatcher
) {
    operator fun invoke(email: String): Boolean = emailMatcher.isValid(email)
}