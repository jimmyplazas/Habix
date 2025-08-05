package dev.alejo.habix.authentication.presentation.signup

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import dev.alejo.habix.R
import dev.alejo.habix.authentication.presentation.signup.components.SignUpForm
import dev.alejo.habix.core.presentation.HabixTitle

@Composable
fun SignUpScreen(
    modifier: Modifier = Modifier,
    viewModel: SignUpViewModel = hiltViewModel(),
    navigateToHome: () -> Unit,
    navigateToLogin: () -> Unit
) {
    val state by viewModel.state.collectAsState()

    LaunchedEffect(viewModel) {
        viewModel.effect.collect { effect ->
            when (effect) {
                is SignUpEffect.NavigateToLogin -> navigateToLogin()
                is SignUpEffect.NavigateToHome -> navigateToHome()
            }
        }
    }

    Column(
        modifier = modifier.fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Image(
            painter = painterResource(id = R.drawable.create_account),
            contentDescription = null,
            modifier = Modifier.scale(1f)
        )
        HabixTitle("Create your account")
        SignUpForm(state = state, onEvent = viewModel::onEvent)
    }
}

@Preview(showBackground = true)
@Composable
private fun SignUpScreenPreview() {
    SignUpScreen(
        navigateToHome = {},
        navigateToLogin = {}
    )
}