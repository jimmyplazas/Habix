package dev.alejo.habix.authentication.presentation.signup.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import dev.alejo.habix.authentication.core.presentation.AuthTextFields
import dev.alejo.habix.core.presentation.HabixButton
import dev.alejo.habix.ui.theme.AppDimens

@Composable
fun SignUpForm(
    modifier: Modifier = Modifier,
    onSignUpClick: () -> Unit,
    onLoginClick: () -> Unit
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(AppDimens.Default)
    ) {
        AuthTextFields()
        HabixButton(
            text = "Create Account",
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = AppDimens.Default),
            isEnabled = true
        ) { onSignUpClick() }
        SignUpOptions { onLoginClick() }
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