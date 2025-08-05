package dev.alejo.habix.authentication.presentation.login.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import dev.alejo.habix.authentication.presentation.login.LoginEvent
import dev.alejo.habix.authentication.presentation.login.LoginState
import dev.alejo.habix.core.presentation.HabixButton
import dev.alejo.habix.core.presentation.HabixPasswordTextField
import dev.alejo.habix.core.presentation.HabixTextField
import dev.alejo.habix.ui.theme.AppDimens

@Composable
fun LoginForm(
    state: LoginState,
    onEvent: (LoginEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.background(
                color = Color.White,
                shape = RoundedCornerShape(topStart = AppDimens.Large, topEnd = AppDimens.Large)
            ),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(AppDimens.Default)
        ) {
            Text(
                "Login with Email",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.tertiary,
                modifier = Modifier
                    .padding(top = AppDimens.Default)
                    .padding(vertical = AppDimens.Tiny)
            )
            HorizontalDivider(
                modifier = Modifier
                    .fillMaxWidth(),
                color = MaterialTheme.colorScheme.background
            )

            LoginTextFields(state = state, onEvent = onEvent)

            HabixButton(
                text = "Login",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = AppDimens.Default),
                isEnabled = !state.isLoading
            ) { onEvent(LoginEvent.Login) }

            LoginOptions { onEvent(LoginEvent.SignUp) }
        }

        if (state.isLoading) {
            CircularProgressIndicator()
        }
    }
}

@Composable
fun LoginTextFields(
    state: LoginState,
    onEvent: (LoginEvent) -> Unit
) {
    val focusManager = LocalFocusManager.current
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HabixTextField(
            value = state.email,
            onValueChange = { onEvent(LoginEvent.EmailChange(it)) },
            placeholder = "Email",
            contentDescription = "Enter email",
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = AppDimens.Tiny)
                .padding(horizontal = AppDimens.Default),
            leadingIcon = Icons.Outlined.Email,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(
                onAny = {
                    focusManager.moveFocus(FocusDirection.Next)
                }
            ),
            isEnabled = !state.isLoading,
            errorMessage = state.emailError
        )

        HabixPasswordTextField(
            value = state.password,
            onValueChange = { onEvent(LoginEvent.PasswordChange(it)) },
            placeholder = "Password",
            contentDescription = "Enter Password",
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = AppDimens.Tiny)
                .padding(horizontal = AppDimens.Default),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onAny = {
                    focusManager.clearFocus()
                    onEvent(LoginEvent.Login)
                }
            ),
            isEnabled = !state.isLoading,
            errorMessage = state.passwordError
        )
    }
}

@Composable
fun LoginOptions(onSignUpClick: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextButton(
            onClick = {}
        ) {
            Text(
                text = "Forgot Password?",
                color = MaterialTheme.colorScheme.tertiary,
                textDecoration = TextDecoration.Underline
            )
        }
        TextButton(
            onClick = onSignUpClick
        ) {
            Text(
                text = buildAnnotatedString {
                    append("Don't have an account?")
                    withStyle(SpanStyle(fontWeight = FontWeight.Bold)) {
                        append(" Sign Up")
                    }
                },
                color = MaterialTheme.colorScheme.tertiary
            )
        }
    }

}

@Preview(showBackground = true)
@Composable
private fun LoginFormPreview() {
    LoginForm(
        state = LoginState(),
        onEvent = {},
        modifier = Modifier
            .background(Color.Black)
            .padding(AppDimens.Default)
    )
}