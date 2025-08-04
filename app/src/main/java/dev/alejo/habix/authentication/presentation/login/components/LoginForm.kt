package dev.alejo.habix.authentication.presentation.login.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import dev.alejo.habix.authentication.core.presentation.AuthTextFields
import dev.alejo.habix.core.presentation.HabixButton
import dev.alejo.habix.ui.theme.AppDimens

@Composable
fun LoginForm(
    modifier: Modifier = Modifier,
    onLoginClick: () -> Unit = {},
    onSignUpClick: () -> Unit = {}
) {
    Column(
        modifier = modifier.background(
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
            modifier = Modifier.padding(top = AppDimens.Default).padding(vertical = AppDimens.Tiny)
        )
        HorizontalDivider(
            modifier = Modifier
                .fillMaxWidth(),
            color = MaterialTheme.colorScheme.background
        )

        AuthTextFields()

        HabixButton(
            text = "Login",
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = AppDimens.Default),
            isEnabled = true
        ) { onLoginClick() }

        LoginOptions { onSignUpClick() }

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
    LoginForm(Modifier.background(Color.Black).padding(AppDimens.Default))
}