package dev.alejo.habix.authentication.domain.usecase

class ValidatePasswordUseCase {
    operator fun invoke(password: String): PasswordResult {
        if (password.isBlank()) {
            return PasswordResult.INVALID_BLANK
        }
        if (password.length < 8) {
            return PasswordResult.INVALID_LENGTH
        }

        val containsLettersAndDigits = password.any { it.isDigit() } && password.any { it.isLetter() }
        if (!containsLettersAndDigits) {
            return PasswordResult.INVALID_CHARACTERS
        }

        val containsUpperCase = password.any { it.isUpperCase() }
        if (!containsUpperCase) {
            return PasswordResult.INVALID_UPPERCASE
        }
        return PasswordResult.VALID
    }
}

enum class PasswordResult {
    VALID,
    INVALID_BLANK,
    INVALID_LENGTH,
    INVALID_CHARACTERS,
    INVALID_UPPERCASE,
}