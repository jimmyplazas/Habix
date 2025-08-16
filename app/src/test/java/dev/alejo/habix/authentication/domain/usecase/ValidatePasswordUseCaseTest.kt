package dev.alejo.habix.authentication.domain.usecase

import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class ValidatePasswordUseCaseTest {

    lateinit var validatePasswordUseCase: ValidatePasswordUseCase

    @Before
    fun setUp() {
        validatePasswordUseCase = ValidatePasswordUseCase()
    }

    @Test
    fun `Password is valid`() {
        val input = "ValidPassword123"
        val result = validatePasswordUseCase(input)
        assertEquals(PasswordResult.VALID, result)
    }

    @Test
    fun `Given empty password, return Invalid password`() {
        val input = ""
        val result = validatePasswordUseCase(input)
        assertEquals(PasswordResult.INVALID_BLANK, result)
    }

    @Test
    fun `Given password with less than 8 characters, return Invalid length`() {
        val input = "Shor1"
        val result = validatePasswordUseCase(input)
        assertEquals(PasswordResult.INVALID_LENGTH, result)
    }

    @Test
    fun `Given password without letters, return Invalid characters`() {
        val input = "12345678"
        val result = validatePasswordUseCase(input)
        assertEquals(PasswordResult.INVALID_CHARACTERS, result)
    }

    @Test
    fun `Given password without numbers, return Invalid characters`() {
        val input = "asdfASDF"
        val result = validatePasswordUseCase(input)
        assertEquals(PasswordResult.INVALID_CHARACTERS, result)
    }

    @Test
    fun `Given password without uppercase letters, return Invalid uppercase`() {
        val input = "lowercase123"
        val result = validatePasswordUseCase(input)
        assertEquals(PasswordResult.INVALID_UPPERCASE, result)
    }

}