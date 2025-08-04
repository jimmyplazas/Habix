package dev.alejo.habix.authentication.presentation.login

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import dev.alejo.habix.authentication.presentation.login.components.LoginBackground
import dev.alejo.habix.authentication.presentation.login.components.LoginForm
import dev.alejo.habix.core.presentation.HabixTitle

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    onLoginClick: () -> Unit,
    onSignUpClick: () -> Unit
) {
    Box(modifier = modifier.fillMaxSize()) {
        LoginBackground()
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.weight(3f))
            HabixTitle("Welcome to")
            HabixTitle("monumental habits")
            Spacer(modifier = Modifier.weight(1f))
            LoginForm(
                onLoginClick = onLoginClick,
                onSignUpClick = onSignUpClick
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun LoginScreenPreview() {
    LoginScreen(
        onLoginClick = {},
        onSignUpClick = {}
    )
}