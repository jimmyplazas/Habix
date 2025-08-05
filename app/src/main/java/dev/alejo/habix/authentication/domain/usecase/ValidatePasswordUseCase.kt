package dev.alejo.habix.authentication.domain.usecase

class ValidatePasswordUseCase {
    operator fun invoke(password: String): PasswordResult {
        if (password.isBlank()) {
            return PasswordResult.Invalid("The password can't be blank")
        }
        if (password.length < 8) {
            return PasswordResult.Invalid("The password needs to consist of at least 8 characters")
        }

        val containsLettersAndDigits = password.any { it.isDigit() } && password.any { it.isLetter() }
        if (!containsLettersAndDigits) {
            return PasswordResult.Invalid("The password needs to contain at least one letter and digit")
        }

        val containsUpperCase = password.any { it.isUpperCase() }
        if (!containsUpperCase) {
            return PasswordResult.Invalid("The password needs to contain at least one uppercase letter")
        }
        return PasswordResult.Valid
    }
}

sealed class PasswordResult {
    object Valid : PasswordResult()
    data class Invalid(val message: String) : PasswordResult()
}