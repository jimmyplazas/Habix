package dev.alejo.habix.authentication.presentation.login

import dev.alejo.habix.authentication.data.repository.FakeAuthRepository
import dev.alejo.habix.authentication.domain.matcher.EmailMatcher
import dev.alejo.habix.authentication.domain.usecase.LoginUseCases
import dev.alejo.habix.authentication.domain.usecase.LoginWithEmailUseCase
import dev.alejo.habix.authentication.domain.usecase.ValidateEmailUseCase
import dev.alejo.habix.authentication.domain.usecase.ValidatePasswordUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class LoginViewModelTest {

    lateinit var viewModel: LoginViewModel
    lateinit var authRepository: FakeAuthRepository
    private val dispatcher = StandardTestDispatcher()
    private val scope = TestScope(dispatcher)

    @Before
    fun setUp() {
        authRepository = FakeAuthRepository()
        val loginUseCases = LoginUseCases(
            loginWithEmail = LoginWithEmailUseCase(authRepository),
            validatePassword = ValidatePasswordUseCase(),
            validateEmail = ValidateEmailUseCase(object : EmailMatcher {
                override fun isValid(email: String): Boolean {
                    return email.isNotEmpty()
                }
            })
        )
        viewModel = LoginViewModel(loginUseCases, dispatcher)
    }

    @Test
    fun `Initial state is empty`() {
        val state = viewModel.state.value
        val expectedState = LoginState(
            email = "",
            password = "",
            emailError = null,
            passwordError = null,
            isLoading = false
        )
        assertEquals(expectedState, state)
    }

    @Test
    fun `Given an email, verify the state updates the email`() {
        val email = "asd@g.co"
        viewModel.onEvent(LoginEvent.EmailChange(email = email))
        val state = viewModel.state.value
        assertEquals(email, state.email)
    }

    @Test
    fun `Given a password, verify the state updates the password`() {
        val password = "Asd123"
        viewModel.onEvent(LoginEvent.PasswordChange(password = password))
        val state = viewModel.state.value
        assertEquals(password, state.password)
    }

    @Test
    fun `Given invalid email, return email error`() {
        val email = ""
        val password = "asdASD123"
        viewModel.onEvent(LoginEvent.EmailChange(email = email))
        viewModel.onEvent(LoginEvent.PasswordChange(password = password))
        viewModel.onEvent(LoginEvent.Login)
        val state = viewModel.state.value
        assertNotNull(state.emailError)
    }

    @Test
    fun `Given valid credentials, start loading, and get logged in`() = scope.runTest {
        val email = "asd@g.co"
        val password = "asdASD123"
        viewModel.onEvent(LoginEvent.EmailChange(email = email))
        viewModel.onEvent(LoginEvent.PasswordChange(password = password))
        viewModel.onEvent(LoginEvent.Login)
        val state = viewModel.state.value
        assert(state.isLoading)
        advanceUntilIdle()
        val effect = viewModel.effect.first()
        assertEquals(LoginEffect.NavigateToHome, effect)
    }

    @Test
    fun `Given valid credentials, start loading, and get server error`() = scope.runTest {
        val email = "asd@g.co"
        val password = "asdASD123"
        authRepository.fakeError = true
        viewModel.onEvent(LoginEvent.EmailChange(email = email))
        viewModel.onEvent(LoginEvent.PasswordChange(password = password))
        viewModel.onEvent(LoginEvent.Login)
        val state = viewModel.state.value
        assert(state.isLoading)
        advanceUntilIdle()
        val stateUpdated = viewModel.state.value
        assert(!stateUpdated.isLoading)
        assertNotNull(stateUpdated.emailError)
    }

    @Test
    fun `Given no credentials, navigate to signup`() = scope.runTest {
        viewModel.onEvent(LoginEvent.SignUp)
        advanceUntilIdle()
        val effect = viewModel.effect.first()
        assertEquals(LoginEffect.NavigateToSignUp, effect)
    }

}