package dev.alejo.habix.authentication.presentation.util

import dev.alejo.habix.authentication.domain.usecase.PasswordResult

object PasswordParser {
    fun fromValidationResult(error: PasswordResult): String? = when (error) {
        PasswordResult.VALID -> null
        PasswordResult.INVALID_BLANK -> "The password can't be blank"
        PasswordResult.INVALID_LENGTH -> "The password needs to consist of at least 8 characters"
        PasswordResult.INVALID_CHARACTERS -> "The password needs to contain at least one letter and digit"
        PasswordResult.INVALID_UPPERCASE -> "The password needs to contain at least one uppercase letter"
    }
}