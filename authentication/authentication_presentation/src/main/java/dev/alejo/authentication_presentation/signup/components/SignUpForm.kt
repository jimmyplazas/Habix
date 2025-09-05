package dev.alejo.authentication_presentation.signup.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.ui.text.withStyle
import dev.alejo.core_presentation.HabixButton
import dev.alejo.core_presentation.HabixPasswordTextField
import dev.alejo.core_presentation.HabixTextField
import dev.alejo.core_ui.theme.AppDimens
import dev.alejo.authentication_presentation.signup.SignUpEvent
import dev.alejo.authentication_presentation.signup.SignUpState

@Composable
fun SignUpForm(
    modifier: Modifier = Modifier,
    state: SignUpState,
    onEvent: (SignUpEvent) -> Unit
) {
    Box(
        modifier = modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(AppDimens.Default)
        ) {
            SignUpTextFields(state = state, onEvent = onEvent)
            HabixButton(
                text = "Create Account",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = AppDimens.Default),
                isEnabled = true
            ) { onEvent(SignUpEvent.SignUp) }
            SignUpOptions { onEvent(SignUpEvent.Login) }
        }

        if (state.isLoading) {
            CircularProgressIndicator()
        }
    }

}

@Composable
fun SignUpTextFields(state: SignUpState, onEvent: (SignUpEvent) -> Unit) {
    val focusManager = LocalFocusManager.current
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HabixTextField(
            value = state.email,
            onValueChange = { onEvent(SignUpEvent.EmailChanged(it)) },
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
            errorMessage = state.emailError,
            backgroundColor = Color.White
        )

        HabixPasswordTextField(
            value = state.password,
            onValueChange = { onEvent(SignUpEvent.PasswordChanged(it)) },
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
                    onEvent(SignUpEvent.SignUp)
                }
            ),
            isEnabled = !state.isLoading,
            errorMessage = state.passwordError,
            backgroundColor = Color.White
        )
    }
}

@Composable
fun SignUpOptions(onSignInClick: () -> Unit) {
    TextButton(
        onClick = onSignInClick
    ) {
        Text(
            text = buildAnnotatedString {
                append("Already have an account?")
                withStyle(SpanStyle(fontWeight = FontWeight.Bold)) {
                    append(" Sign In")
                }
            },
            color = MaterialTheme.colorScheme.tertiary
        )
    }
}